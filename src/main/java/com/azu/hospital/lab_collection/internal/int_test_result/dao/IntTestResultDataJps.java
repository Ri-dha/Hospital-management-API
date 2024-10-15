package com.azu.hospital.lab_collection.internal.int_test_result.dao;

import com.azu.hospital.lab_collection.internal.int_test_result.entity.IntTestResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("internalTestResultRepo")
public class IntTestResultDataJps implements IntTestResultDao {

    private final IntTestResultRepository intTestResultRepository;

    public IntTestResultDataJps(IntTestResultRepository intTestResultRepository) {
        this.intTestResultRepository = intTestResultRepository;
    }

    @Override
    public List<IntTestResult> getTestsByTestRequestId(Long id) {
        return intTestResultRepository.getAllByIntTestRequestId(id);
    }

    @Override
    public IntTestResult createTestResult(IntTestResult testResult) {
        return intTestResultRepository.save(testResult);
    }

    @Override
    public List<IntTestResult> createManyTestResult(List<IntTestResult> testResults) {
        return intTestResultRepository.saveAll(testResults);
    }

    @Override
    public List<IntTestResult> getAllByByInternalLabTestId(Long internalLabTest_id) {
        return intTestResultRepository.findAllResultsWithRequestsByInternalLabTestId(internalLabTest_id);
    }

    @Override
    public List<IntTestResult> getAllInternalLabResultByPatientId(Long patientId) {
        return intTestResultRepository.findAllByResultByPatientId(patientId);
    }
}
