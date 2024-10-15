package com.azu.hospital.lab_collection.external.ext_tests_request.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.external.dao.ExternalLabDao;
import com.azu.hospital.lab_collection.external.dto.ExternalLabDtoMapper;
import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.external.ext_tests_request.dao.ExtTestRequestDao;
import com.azu.hospital.lab_collection.external.ext_tests_request.dto.ExtTestRequestDto;
import com.azu.hospital.lab_collection.external.ext_tests_request.dto.ExtTestRequestDtoMapper;
import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import com.azu.hospital.lab_collection.external.ext_tests_request.request.CreateTestsForExternalLabTest;
import com.azu.hospital.lab_collection.external.ext_tests_request.request.TestRequestDataDataRequest;
import com.azu.hospital.lab_collection.test_list.dao.LabListDao;
import com.azu.hospital.lab_collection.test_list.entity.LabList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class ExtTestRequestService {

    private final ExtTestRequestDao extTestRequestDao;

    private final LabListDao labListDao;

    private final ExternalLabDao externalLabDao;

    private final ExtTestRequestDtoMapper mapper;

    private final ExternalLabDtoMapper externalLabDtoMapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ExtTestRequestService(
            @Qualifier("testRequestRepo") ExtTestRequestDao extTestRequestDao,
            @Qualifier("externalLabRepo") ExternalLabDao externalLabDao,
            @Qualifier("labListRepo") LabListDao labListDao,
            ExtTestRequestDtoMapper mapper,
            ExternalLabDtoMapper externalLabDtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.extTestRequestDao = extTestRequestDao;
        this.externalLabDao = externalLabDao;
        this.mapper = mapper;
        this.labListDao = labListDao;
        this.externalLabDtoMapper = externalLabDtoMapper;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public List<ExtTestRequestDto> createTestForExtLabTest(
            Long getExtTestId ,List<TestRequestDataDataRequest> test) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        ExternalLabTest externalLabTest = externalLabDao.findById(getExtTestId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("labTestNotFound")+" "+getExtTestId
                )
        );

        List<ExtTestRequest> extTestRequests = new ArrayList<>();

        for (TestRequestDataDataRequest tests : test) {
            LabList labList = labListDao.findLabListById(tests.getLabTestId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound"))
            );

            ExtTestRequest extTestRequest = new ExtTestRequest(tests.getSpots());
            extTestRequest.setLabList(labList);
            extTestRequest.setExternalLabTest(externalLabTest);
            extTestRequest.setNote(tests.getNote());
            extTestRequest.setTestName(tests.getTestName());
            extTestRequest.setNote(tests.getNote());
            extTestRequests.add(extTestRequest);
        }

        extTestRequests = extTestRequestDao.createManyTestRequest(extTestRequests);

        return extTestRequests.stream().map(mapper::toDto).collect(Collectors.toList());

    }


    public List<ExtTestRequestDto> getTestsByExtTestId(Long externalTestId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ExternalLabTest externalLabTest = externalLabDao.findById(externalTestId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        return externalLabTest.getTestRequests().
                stream().
                map(mapper::toDto).
                collect(Collectors.toList());
    }
}
