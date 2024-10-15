package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao.repository;

import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.NonSedatedPatientAssessment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface NonSedatedPatientAssessmentJpaRepository extends JpaRepository<NonSedatedPatientAssessment, Long> {
  @Query("SELECT chart FROM NonSedatedPatientAssessment chart WHERE chart.patient.id = :patientId")
  List<NonSedatedPatientAssessment> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
