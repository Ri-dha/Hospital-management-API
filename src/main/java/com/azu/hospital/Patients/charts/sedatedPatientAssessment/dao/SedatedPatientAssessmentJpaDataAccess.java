package com.azu.hospital.Patients.charts.sedatedPatientAssessment.dao;

import com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities.SedatedPatientAssessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("SedatedPatientAssessmentJpaDataAccess")
public class SedatedPatientAssessmentJpaDataAccess implements SedatedPatientAssessmentDao{

  private final SedatedPatientAssessmentJpaRepository repository;

  @Autowired
  public SedatedPatientAssessmentJpaDataAccess(SedatedPatientAssessmentJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public SedatedPatientAssessment createNewChart(SedatedPatientAssessment chart) {
    return repository.save(chart);
  }

  @Override
  public SedatedPatientAssessment updateExistsChart(SedatedPatientAssessment update) {
    return repository.save(update);
  }

  @Override
  public Optional<SedatedPatientAssessment> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<SedatedPatientAssessment> getAllCharts(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
