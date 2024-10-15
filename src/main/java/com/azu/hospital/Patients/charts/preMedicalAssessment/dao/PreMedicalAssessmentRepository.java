package com.azu.hospital.Patients.charts.preMedicalAssessment.dao;

import com.azu.hospital.Patients.charts.preMedicalAssessment.entity.PreMedicalAssessmentChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PreMedicalAssessmentRepository extends JpaRepository<PreMedicalAssessmentChart, Long> {
  @Query("SELECT chart FROM PreMedicalAssessmentChart chart WHERE chart.id = :patientId ")
  List<PreMedicalAssessmentChart> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
