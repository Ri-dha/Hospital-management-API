package com.azu.hospital.lab_collection.external.ext_test_result.dao;

import com.azu.hospital.lab_collection.external.ext_test_result.entity.ExtTestResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("testResultRepo")
public class ExtTestResultDataJps implements ExtTestResultDao {

    private final ExtTestResultRepository extTestResultRepository;

    public ExtTestResultDataJps(ExtTestResultRepository extTestResultRepository) {
        this.extTestResultRepository = extTestResultRepository;
    }

    @Override
    public List<ExtTestResult> getTestsByTestRequestId(Long id) {
        return extTestResultRepository.getAllByExtTestRequestId(id);
    }

    @Override
    public ExtTestResult createTestResult(ExtTestResult testResult) {
        return extTestResultRepository.save(testResult);
    }

    @Override
    public List<ExtTestResult> createManyTestResult(List<ExtTestResult> testResults) {
        return extTestResultRepository.saveAll(testResults);
    }
}
