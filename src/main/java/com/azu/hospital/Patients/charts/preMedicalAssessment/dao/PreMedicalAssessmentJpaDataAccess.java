package com.azu.hospital.Patients.charts.preMedicalAssessment.dao;


import com.azu.hospital.Patients.charts.preMedicalAssessment.entity.PreMedicalAssessmentChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PreMedicalAssessmentJpaDataAccess")
public class PreMedicalAssessmentJpaDataAccess implements PreMedicalAssessmentDao {

  private final PreMedicalAssessmentRepository repository;

  @Autowired
  public PreMedicalAssessmentJpaDataAccess(PreMedicalAssessmentRepository repository) {
    this.repository = repository;
  }

  @Override
  public PreMedicalAssessmentChart createNewChart(PreMedicalAssessmentChart chart) {
    return repository.save(chart);
  }

  @Override
  public PreMedicalAssessmentChart updateExistsChart(PreMedicalAssessmentChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<PreMedicalAssessmentChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<PreMedicalAssessmentChart> getAllCharts(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
