package com.azu.hospital.lab_collection.external.ext_tests_request.dao;

import com.azu.hospital.lab_collection.RequestCountData;
import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("testRequestRepo")
public class ExtTestRequestDataJps implements ExtTestRequestDao {

    private final ExtTestRequestRepository testRequestRepository;

    @Autowired
    public ExtTestRequestDataJps(ExtTestRequestRepository testRequestRepository) {
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
    public List<ExtTestRequest> getTestsByExtTestId(Long id) {
        return testRequestRepository.findAllByExternalLabTest_Id(id);
    }

    @Override
    public ExtTestRequest createTestRequest(ExtTestRequest testRequest) {
        return testRequestRepository.save(testRequest);
    }

    @Override
    public List<ExtTestRequest> createManyTestRequest(List<ExtTestRequest> testRequests) {
        return testRequestRepository.saveAll(testRequests);
    }

    @Override
    public Optional<ExtTestRequest> findById(Long id) {
        return testRequestRepository.findById(id);
    }

    @Override
    public void updateTestRequest(ExtTestRequest testRequest) {
        testRequestRepository.save(testRequest);
    }

    @Override
    public void createRequest(List<ExtTestRequest> testRequests) {
        testRequestRepository.saveAll(new ArrayList<>(testRequests));
    }
}
