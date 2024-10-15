package com.azu.hospital.Patients.charts.nursingAssessment.dao;

import com.azu.hospital.Patients.charts.nursingAssessment.entity.NursingAssessmentChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NursingAssessmentChartRepository extends JpaRepository<NursingAssessmentChart, Long> {
  List<NursingAssessmentChart> findAllByPatientId(@Param("patientId") Long patientId);
}
