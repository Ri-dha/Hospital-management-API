package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.repositories.PatientExperiencingRepository;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PatientExperiencingJpaDataAccess")
public class PatientExperiencingJpaDataAccess implements PatientExperiencingDao {

  private final PatientExperiencingRepository repository;

  @Autowired
  public PatientExperiencingJpaDataAccess(PatientExperiencingRepository repository) {
    this.repository = repository;
  }

  @Override
  public void createNewExperiencingChart(List<PatientExperiencing> chart) {
    repository.saveAll(chart);
  }

  @Override
  public PatientExperiencing updateExistsExperiencingChart(PatientExperiencing update) {
    return repository.save(update);
  }

  @Override
  public Optional<PatientExperiencing> findExperiencingChartById(Long patientChartId) {
    return repository.findById(patientChartId);
  }

  @Override
  public List<PatientExperiencing> getAllExperiencingCharts(Long chartId) {
    return repository.findAllExperiencingByChartId(chartId);
  }
}
