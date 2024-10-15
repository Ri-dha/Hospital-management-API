package com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.dao;

import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.entity.ExternalUltrasoundResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExternalUltrasoundResultRepository extends JpaRepository<ExternalUltrasoundResult, Long> {


    Optional<ExternalUltrasoundResult> findByExternalUltrasoundTestId(Long testId);
}
