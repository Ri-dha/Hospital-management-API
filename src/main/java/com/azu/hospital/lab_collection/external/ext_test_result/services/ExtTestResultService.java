package com.azu.hospital.lab_collection.external.ext_test_result.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.external.dao.ExternalLabDao;
import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.external.ext_test_result.dao.ExtTestResultDao;
import com.azu.hospital.lab_collection.external.ext_test_result.dto.ExtTestResultDto;
import com.azu.hospital.lab_collection.external.ext_test_result.dto.ExtTestResultDtoMapper;
import com.azu.hospital.lab_collection.external.ext_test_result.entity.ExtTestResult;
import com.azu.hospital.lab_collection.external.ext_test_result.request.TestResultDataRequest;
import com.azu.hospital.lab_collection.external.ext_test_result.request.TestResultDataRequestList;
import com.azu.hospital.lab_collection.external.ext_tests_request.dao.ExtTestRequestDao;
import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.lab.LabRequestStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class ExtTestResultService {
    private final ExternalLabDao externalLabDao;

    private final ExtTestResultDao extTestResultDao;

    private final ExtTestRequestDao extTestRequestDao;

    private final ExtTestResultDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    public ExtTestResultService(
            ExternalLabDao externalLabDao, @Qualifier("testResultRepo") ExtTestResultDao extTestResultDao,
            @Qualifier("testRequestRepo") ExtTestRequestDao extTestRequestDao,
            ExtTestResultDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.externalLabDao = externalLabDao;
        this.extTestResultDao = extTestResultDao;
        this.extTestRequestDao = extTestRequestDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    @Transactional
    public List<ExtTestResultDto> createResultForTest(List<TestResultDataRequestList>  result) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Long extTestId = 0L;

        List<ExtTestResult> extTestResults = new ArrayList<>();


        for (TestResultDataRequestList results : result){

            ExtTestRequest extTestRequest = extTestRequestDao.findById(results.testId()).orElseThrow(
                    ()->new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

            extTestId = extTestRequest.getExternalLabTest().getId();

            if (extTestRequest.test().getState() != LabRequestStatusEnum.SampleReceived){
                throw new BadRequestException(
                        messages.getString("cannotAccept")
                );
            }

            for (TestResultDataRequest data : results.results()){
                ExtTestResult extTestResult = new ExtTestResult(
                        data.resultName(),
                        data.testResult(),
                        data.normalValue()
                );
                extTestResult.setExtTestRequest(extTestRequest);
                extTestResults.add(extTestResult);
            }
            extTestRequest.setNote(results.note());
        }


        extTestResults = extTestResultDao.createManyTestResult(extTestResults);

        ExternalLabTest externalLabTest = externalLabDao.findById(extTestId).orElseThrow();

        externalLabTest.setState(LabRequestStatusEnum.Complete);

        externalLabDao.updateExternalTest(externalLabTest);

        return extTestResults.stream().map(mapper::toDto).collect(Collectors.toList());
    }




    public List<ExtTestResultDto> getTestResultByTestId(Long testId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ExtTestRequest extTestRequest = extTestRequestDao.findById(testId).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        return extTestResultDao.
                getTestsByTestRequestId(testId).
                stream().
                map(mapper::toDto).
                collect(Collectors.toList());
    }
}
