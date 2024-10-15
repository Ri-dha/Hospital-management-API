package com.azu.hospital.lab_collection.internal.int_test_result.services;

import com.azu.hospital.accounting.all_item_expanse.Labs.service.IPatientLabExpanseResultTableService;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.internal.dao.InternalLabDao;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_test_result.dao.IntTestResultDao;
import com.azu.hospital.lab_collection.internal.int_test_result.dto.IntTestResultDto;
import com.azu.hospital.lab_collection.internal.int_test_result.dto.IntTestResultDtoMapper;
import com.azu.hospital.lab_collection.internal.int_test_result.entity.IntTestResult;
import com.azu.hospital.lab_collection.internal.int_test_result.request.InternalTestDataRequestList;
import com.azu.hospital.lab_collection.internal.int_test_result.request.InternalTestResultDataRequest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dao.IntTestRequestDao;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import com.azu.hospital.lab_collection.internal.int_tests_request.util.IntTestRequestState;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileRepository;
import com.azu.hospital.utils.files.FileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntTestResultService {

    private final IntTestResultDao intTestResultDao;

    private final IntTestRequestDao intTestRequestDao;
    private final InternalLabDao internalLabDao;

    private final IntTestResultDtoMapper mapper;
    private final FileService fileService;
    private final FileRepository fileRepository;

    private final IPatientLabExpanseResultTableService patientLabExpanseResultTableService;

    public IntTestResultService(
            @Qualifier("internalTestResultRepo") IntTestResultDao intTestResultDao,
            @Qualifier("internalTestRequestRepo") IntTestRequestDao intTestRequestDao,
            @Qualifier("internalLabRepo") InternalLabDao internalLabDao,
            @Qualifier("intTestResultDtoMapper") IntTestResultDtoMapper mapper,
            @Qualifier("fileService") FileService fileService,
            FileRepository fileRepository, @Qualifier("PatientLabExpanseResultTableServiceImp") IPatientLabExpanseResultTableService patientLabExpanseResultTableService) {
        this.intTestResultDao = intTestResultDao;
        this.intTestRequestDao = intTestRequestDao;
        this.internalLabDao = internalLabDao;
        this.mapper = mapper;
        this.fileService = fileService;
        this.fileRepository = fileRepository;
        this.patientLabExpanseResultTableService = patientLabExpanseResultTableService;
    }

    @Transactional
    public List<IntTestResultDto> createResultForTest(List<InternalTestDataRequestList> result) throws IOException {


        Long intTestId = 0L;

        List<IntTestResult> intTestResults = new ArrayList<>();

        for (InternalTestDataRequestList results : result) {

            IntTestRequest intTestRequest = intTestRequestDao.findById(results.testId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Test isn't Found")
                    );

            if (results.file() != null){
                List<File> fileList=  uploadMultiFile(results.file());
                intTestRequest.setTestsFiles(fileList);
            }

            intTestId = intTestRequest.getInternalLabTest().getId();

            if (intTestRequest.getInternalLabTest().getState() != InternalLabRequestStatusEnum.SampleReceived) {
                throw new BadRequestException("The Test State Must Sample Received First");
            }


            for (InternalTestResultDataRequest data : results.results()) {
                IntTestResult intTestResult = new IntTestResult(
                        data.resultName(),
                        data.testResult(),
                        data.normalValue()
                );
                intTestResult.setIntTestRequest(intTestRequest);
                intTestResults.add(intTestResult);


            }
            intTestRequest.setState(IntTestRequestState.COMPLETED);
            intTestRequest.setNote(results.note());
            intTestRequestDao.updateTestRequest(intTestRequest);
            patientLabExpanseResultTableService.addPatientLabExpanseResultTable(intTestRequest.getInternalLabTest().getPatient().getId());
        }

        intTestResults = intTestResultDao.createManyTestResult(intTestResults);

        InternalLabTest internalLabTest = internalLabDao.findById(intTestId).orElseThrow();

        internalLabTest.setState(InternalLabRequestStatusEnum.Complete);

        internalLabDao.updateInternalTest(internalLabTest);



        return intTestResults.stream().map(mapper::toDto).collect(Collectors.toList());
    }


    public List<IntTestResultDto> getTestResultByTestId(Long testId) {
        IntTestRequest intTestRequest = intTestRequestDao.findById(testId).orElseThrow(
                () -> new ResourceNotFoundException("Test Doesn't Found")
        );

        return intTestResultDao.
                getTestsByTestRequestId(testId).
                stream().
                map(mapper::toDto).
                collect(Collectors.toList());
    }

    public List<IntTestResultDto> getTestResultByPatientId(Long patientId) {
        return intTestResultDao.getAllInternalLabResultByPatientId(patientId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }



    public List<File> uploadMultiFile(List<MultipartFile> files) throws IOException {
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = fileService.saveFile(file);
                File newFile = new File(file.getContentType(), "Patient", url);
                fileList.add(newFile);
            }
        }
        return fileList;
    }


}