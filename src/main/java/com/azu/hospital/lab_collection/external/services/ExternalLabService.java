package com.azu.hospital.lab_collection.external.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.external.dao.ExternalLabDao;
import com.azu.hospital.lab_collection.external.dto.ExternalLabDto;
import com.azu.hospital.lab_collection.external.dto.ExternalLabDtoMapper;
import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.external.ext_tests_request.dao.ExtTestRequestDao;
import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import com.azu.hospital.lab_collection.external.request.CreateExternalLabRequest;
import com.azu.hospital.utils.enums.lab.LabRequestStatusEnum;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalLabService {

    private final ExternalLabDao externalLabDao;

    private final ExtTestRequestDao extTestRequestDao;

    private final ExternalLabDtoMapper mapper;

    @Autowired
    public ExternalLabService(
            @Qualifier("externalLabRepo") ExternalLabDao externalLabDao,
            ExtTestRequestDao extTestRequestDao,
            ExternalLabDtoMapper mapper
    ) {
        this.externalLabDao = externalLabDao;
        this.extTestRequestDao = extTestRequestDao;
        this.mapper = mapper;
    }

    @Transactional
    public ExternalLabDto createExternalLab(
            CreateExternalLabRequest request

    ) {
        ExternalLabTest externalLabTest = new ExternalLabTest(
                request.getPatientName(),
                request.getDoctorName(),
                request.getGender(),
                request.getAge(),
                request.getDx(),
                request.getAllergy(),
                LabRequestStatusEnum.GettingSample,
                request.getNote(),
                request.getHeight(),
                request.getWeight()

        );

        ExternalLabTest finalExternalLabTest = externalLabTest;
        request.getTest().
                stream()
                .map((d) -> {
                            ExtTestRequest testRequest = new ExtTestRequest();
                            testRequest.setSpots(d.getSpots());
                            testRequest.setTestName(d.getTestName());
                            finalExternalLabTest.setTestRequests(List.of());
                            extTestRequestDao.createRequest(List.of(testRequest));
                            testRequest.setExternalLabTest(finalExternalLabTest);
                            return testRequest;
                        }
                )
                .collect(Collectors.toList());

        externalLabTest = externalLabDao.createNewExternalTest(externalLabTest);

        return mapper.apply(externalLabTest);
    }


}
