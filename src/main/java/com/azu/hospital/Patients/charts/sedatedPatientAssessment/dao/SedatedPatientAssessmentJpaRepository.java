package com.azu.hospital.Patients.charts.sedatedPatientAssessment.dao;

import com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities.SedatedPatientAssessment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface SedatedPatientAssessmentJpaRepository
        extends JpaRepository<SedatedPatientAssessment, Long> {
  @Query("SELECT chart FROM SedatedPatientAssessment chart WHERE chart.patient.id = :patientId ORDER BY coalesce(chart.createdAt,chart.updatedAt)DESC")
  List<SedatedPatientAssessment> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
