package com.azu.hospital.Patients.charts.postAnestheticEvaluation.dao;

import com.azu.hospital.Patients.charts.postAnestheticEvaluation.entity.PostAnestheticEvaluationChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PostAnestheticEvaluationChartJpaRepository extends JpaRepository<PostAnestheticEvaluationChart, Long> {
  @Query("SELECT p FROM PostAnestheticEvaluationChart p WHERE p.patient.id = :patientId ORDER BY coalesce(p.createdAt,p.updatedAt)DESC ")
  List<PostAnestheticEvaluationChart> getAllPostAnestheticEvaluationChartByPatientId(@Param("patientId") Long patientId);
}
