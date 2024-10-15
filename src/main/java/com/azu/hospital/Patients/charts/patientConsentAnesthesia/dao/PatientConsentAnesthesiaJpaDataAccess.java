package com.azu.hospital.Patients.charts.patientConsentAnesthesia.dao;

import com.azu.hospital.Patients.charts.patientConsentAnesthesia.entity.PatientConsentAnesthesiaChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PatientConsentAnesthesiaJpaDataAccess")
public class PatientConsentAnesthesiaJpaDataAccess implements PatientConsentAnesthesiaDao{

  private final PatientConsentAnesthesiaJpaRepository repository;

  @Autowired
  public PatientConsentAnesthesiaJpaDataAccess(PatientConsentAnesthesiaJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public PatientConsentAnesthesiaChart createNewChart(PatientConsentAnesthesiaChart chart) {
    return repository.save(chart);
  }

  @Override
  public PatientConsentAnesthesiaChart updateExistsChart(PatientConsentAnesthesiaChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<PatientConsentAnesthesiaChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<PatientConsentAnesthesiaChart> getAllCharts(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
