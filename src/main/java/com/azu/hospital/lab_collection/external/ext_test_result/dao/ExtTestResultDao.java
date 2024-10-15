package com.azu.hospital.lab_collection.external.ext_test_result.dao;

import com.azu.hospital.lab_collection.external.ext_test_result.entity.ExtTestResult;

import java.util.List;

public interface ExtTestResultDao {

    List<ExtTestResult> getTestsByTestRequestId(Long id);
    ExtTestResult createTestResult(ExtTestResult testResult);

    List<ExtTestResult> createManyTestResult(List<ExtTestResult> testResults);


}
