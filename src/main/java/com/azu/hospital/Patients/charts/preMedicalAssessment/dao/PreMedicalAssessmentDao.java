package com.azu.hospital.Patients.charts.preMedicalAssessment.dao;

import com.azu.hospital.Patients.charts.preMedicalAssessment.entity.PreMedicalAssessmentChart;

import java.util.List;
import java.util.Optional;

public interface PreMedicalAssessmentDao {

  PreMedicalAssessmentChart createNewChart(PreMedicalAssessmentChart chart);

  PreMedicalAssessmentChart updateExistsChart(PreMedicalAssessmentChart update);

  Optional<PreMedicalAssessmentChart> findChartById(Long chartId);

  List<PreMedicalAssessmentChart> getAllCharts(Long patientId);


}
