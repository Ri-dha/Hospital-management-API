package com.azu.hospital.Patients.charts.patientAssessment.dao;


import com.azu.hospital.Patients.charts.patientAssessment.entity.PatientAssessmentChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PatientAssessmentJpaRepository extends JpaRepository<PatientAssessmentChart, Long> {
  @Query("SELECT chart FROM PatientAssessmentChart chart WHERE chart.patient.id = :patientId")
  List<PatientAssessmentChart> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
