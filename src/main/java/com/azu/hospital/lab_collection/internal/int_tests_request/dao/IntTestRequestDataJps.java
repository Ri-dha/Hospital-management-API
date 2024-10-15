package com.azu.hospital.lab_collection.internal.int_tests_request.dao;

import com.azu.hospital.lab_collection.RequestCountData;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("internalTestRequestRepo")
public class IntTestRequestDataJps implements IntTestRequestDao {

    private final IntTestRequestRepository testRequestRepository;

    @Autowired
    public IntTestRequestDataJps(IntTestRequestRepository testRequestRepository) {
        this.testRequestRepository = testRequestRepository;
    }

    @Override
    public Long countAll() {
        return testRequestRepository.count();
    }

    @Override
    public List<RequestCountData> getTop15By() {
        return testRequestRepository.getTop15By();
    }

    @Override
    public List<IntTestRequest> getTestsByIntTestId(Long id) {
        return testRequestRepository.findAllByInternalLabTest_Id(id);
    }

    @Override
    public IntTestRequest createTestRequest(IntTestRequest testRequest) {
        return testRequestRepository.save(testRequest);
    }

    @Override
    public List<IntTestRequest> createManyTestRequest(List<IntTestRequest> testRequests) {
        return testRequestRepository.saveAll(testRequests);
    }

    @Override
    public Optional<IntTestRequest> findById(Long id) {
        return testRequestRepository.findById(id);
    }

    @Override
    public void updateTestRequest(IntTestRequest testRequest) {
        testRequestRepository.save(testRequest);
    }

    @Override
    public void createRequest(List<IntTestRequest> testRequests) {
        testRequestRepository.saveAll(new ArrayList<>(testRequests));
    }

    @Override
    public List<IntTestRequest> getAllByInternalLabTestId(Long internalLabTest_id) {
        return testRequestRepository.findAllByInternalLabTestIdWithResults(internalLabTest_id);
    }

    @Override
    public Optional<IntTestRequest> getAllWithPatientData(Long testId) {
        return testRequestRepository.getAllByTestId(testId);
    }

    @Override
    public List<IntTestRequest> getAllCompletedByPatientId(Long patientId) {
        return testRequestRepository.getAllCompletedByPatientId(patientId);
    }

    @Override
    public Integer countAllByPatientId(Long patientId,String testName) {
        return testRequestRepository.countAllByPatientId(patientId,testName);
    }


}
