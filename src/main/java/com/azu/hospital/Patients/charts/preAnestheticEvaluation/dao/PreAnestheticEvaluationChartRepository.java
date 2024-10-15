package com.azu.hospital.Patients.charts.preAnestheticEvaluation.dao;

import com.azu.hospital.Patients.charts.preAnestheticEvaluation.entity.PreAnestheticEvaluationChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PreAnestheticEvaluationChartRepository
        extends JpaRepository<PreAnestheticEvaluationChart, Long> {
  @Query("SELECT chart FROM PreAnestheticEvaluationChart chart WHERE chart.patient.id = :patientId ORDER BY coalesce(chart.createdAt,chart.updatedAt)DESC")
  List<PreAnestheticEvaluationChart> getAllChartsByPatientId(@Param("patientId") Long patientId);
}
