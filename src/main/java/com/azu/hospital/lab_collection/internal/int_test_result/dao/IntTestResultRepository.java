package com.azu.hospital.lab_collection.internal.int_test_result.dao;

import com.azu.hospital.lab_collection.internal.int_test_result.entity.IntTestResult;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface IntTestResultRepository extends JpaRepository<IntTestResult, Long> {

    List<IntTestResult> getAllByIntTestRequestId(Long intTestRequest_id);

    @Query("SELECT r FROM IntTestResult r JOIN FETCH r.intTestRequest WHERE r.intTestRequest.internalLabTest.id = :internalLabTestId")
    List<IntTestResult> findAllResultsWithRequestsByInternalLabTestId(@Param("internalLabTestId") Long internalLabTestId);

    @Query("SELECT r FROM IntTestResult r WHERE r.intTestRequest.internalLabTest.patient.id = :patientId ORDER BY COALESCE(r.updatedAt, r.createdAt) DESC")
    List<IntTestResult> findAllByResultByPatientId(@Param("patientId") Long patientId);

}
