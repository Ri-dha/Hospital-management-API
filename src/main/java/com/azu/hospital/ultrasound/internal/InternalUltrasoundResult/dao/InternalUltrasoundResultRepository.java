package com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.dao;

import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.entity.InternalUltrasoundResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InternalUltrasoundResultRepository extends JpaRepository<InternalUltrasoundResult, Long> {


    Optional<InternalUltrasoundResult> findByInternalUltrasoundTestId(Long testId);
}
