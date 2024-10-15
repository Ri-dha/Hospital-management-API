package com.azu.hospital.radiology.external.ExternalRadiologyResult.dao;

import com.azu.hospital.radiology.external.ExternalRadiologyResult.entity.ExternalRadiologyResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExternalRadiologyResultRepository extends JpaRepository<ExternalRadiologyResult, Long> {


    Optional<ExternalRadiologyResult> findByExternalRadiologyTestId(Long testId);
}
