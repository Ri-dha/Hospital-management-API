package com.azu.hospital.lab_collection.external.ext_test_result.dao;

import com.azu.hospital.lab_collection.external.ext_test_result.entity.ExtTestResult;
import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ExtTestResultRepository extends JpaRepository<ExtTestResult, Long> {

    List<ExtTestResult> getAllByExtTestRequestId(Long extTestRequest_id);

}
