package com.azu.hospital.lab_collection.external.ext_tests_request.dao;

import com.azu.hospital.lab_collection.RequestCountData;
import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;

import java.util.List;
import java.util.Optional;

public interface ExtTestRequestDao {

    Long countAll();

    List<RequestCountData> getTop15By();

    List<ExtTestRequest> getTestsByExtTestId(Long id);

    ExtTestRequest createTestRequest(ExtTestRequest testRequest);

    List<ExtTestRequest> createManyTestRequest(List<ExtTestRequest> testRequests);

    Optional<ExtTestRequest> findById(Long id);

    void updateTestRequest(ExtTestRequest testRequest);

    void createRequest(List<ExtTestRequest> testRequests);


}
