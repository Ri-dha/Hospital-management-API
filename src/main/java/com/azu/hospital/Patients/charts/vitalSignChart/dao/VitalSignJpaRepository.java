package com.azu.hospital.Patients.charts.vitalSignChart.dao;

import com.azu.hospital.Patients.charts.vitalSignChart.entity.VitalSign;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface VitalSignJpaRepository extends JpaRepository<VitalSign, Long> {
  @Query("SELECT vitalSign FROM VitalSign vitalSign WHERE vitalSign.patient.id = :patientId ORDER BY coalesce(vitalSign.createdAt,vitalSign.updatedAt)DESC")
  List<VitalSign> getAllByPatientId(@Param("patientId") Long patientId);
}
