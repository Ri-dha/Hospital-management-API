package com.azu.hospital.radiology.internal.InternalRadiologyResult.dao;

import com.azu.hospital.radiology.internal.InternalRadiologyResult.entity.InternalRadiologyResult;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface InternalRadiologyResultRepository extends JpaRepository<InternalRadiologyResult, Long> {
    // TODO: 1/15/2024 add query
    Optional<InternalRadiologyResult> findByInternalRadiologyTestId(@Param("testId") Long testId);

    @Query("SELECT r FROM InternalRadiologyResult r WHERE r.internalRadiologyTest.patient.id = :patientId " +
            "AND (:radiologyType IS NULL OR r.internalRadiologyTest.type = :radiologyType) " +
            "ORDER BY COALESCE(r.createdAt, r.updatedAt) DESC")
    List<InternalRadiologyResult> findAllByInternalRadiologyTest_PatientIdAndType(
            @Param("patientId") Long patientId,
            @Param("radiologyType") RadiologyTypeEnum radiologyType);




}
