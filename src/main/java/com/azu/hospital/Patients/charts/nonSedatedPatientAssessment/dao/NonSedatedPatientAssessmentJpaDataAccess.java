package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao;

import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao.repository.NonSedatedPatientAssessmentJpaRepository;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.NonSedatedPatientAssessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("NonSedatedPatientAssessmentJpaDataAccess")
public class NonSedatedPatientAssessmentJpaDataAccess implements NonSedatedPatientAssessmentDao {

  private final NonSedatedPatientAssessmentJpaRepository repository;

  @Autowired
  public NonSedatedPatientAssessmentJpaDataAccess(NonSedatedPatientAssessmentJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public NonSedatedPatientAssessment createNewChart(NonSedatedPatientAssessment chart) {
    return repository.save(chart);
  }

  @Override
  public NonSedatedPatientAssessment updateExistsChart(NonSedatedPatientAssessment update) {
    return repository.save(update);
  }

  @Override
  public Optional<NonSedatedPatientAssessment> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<NonSedatedPatientAssessment> getAllCharts(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
