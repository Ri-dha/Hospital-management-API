package com.azu.hospital.lab_collection.internal.int_tests_request.dao;

import com.azu.hospital.lab_collection.RequestCountData;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;

import java.util.List;
import java.util.Optional;

public interface IntTestRequestDao {

    Long countAll();

    List<RequestCountData> getTop15By();

    List<IntTestRequest> getTestsByIntTestId(Long id);

    IntTestRequest createTestRequest(IntTestRequest testRequest);

    List<IntTestRequest> createManyTestRequest(List<IntTestRequest> testRequests);

    Optional<IntTestRequest> findById(Long id);

    void updateTestRequest(IntTestRequest testRequest);

    void createRequest(List<IntTestRequest> testRequests);

    List<IntTestRequest> getAllByInternalLabTestId(Long internalLabTest_id);

   Optional<IntTestRequest> getAllWithPatientData(Long testId);
    List<IntTestRequest> getAllCompletedByPatientId(Long patientId);

    Integer countAllByPatientId(Long patientId,String testName);
}
