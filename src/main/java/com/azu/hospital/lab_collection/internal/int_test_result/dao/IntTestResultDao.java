package com.azu.hospital.lab_collection.internal.int_test_result.dao;

import com.azu.hospital.lab_collection.internal.int_test_result.entity.IntTestResult;

import java.util.List;

public interface IntTestResultDao {

    List<IntTestResult> getTestsByTestRequestId(Long id);

    IntTestResult createTestResult(IntTestResult testResult);

    List<IntTestResult> createManyTestResult(List<IntTestResult> testResults);

    List<IntTestResult> getAllByByInternalLabTestId(Long internalLabTest_id);

    List<IntTestResult> getAllInternalLabResultByPatientId(Long patientId);


}
