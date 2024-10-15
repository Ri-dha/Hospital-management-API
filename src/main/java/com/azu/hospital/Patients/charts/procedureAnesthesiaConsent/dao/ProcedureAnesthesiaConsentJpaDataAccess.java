package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dao;

import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.entity.ProcedureAnesthesiaConsentChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ProcedureAnesthesiaConsentJpaDataAccess")
public class ProcedureAnesthesiaConsentJpaDataAccess implements ProcedureAnesthesiaConsentDao{

  private final ProcedureAnesthesiaConsentJpaRepository repository;

  @Autowired
  public ProcedureAnesthesiaConsentJpaDataAccess(ProcedureAnesthesiaConsentJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public ProcedureAnesthesiaConsentChart createNewChart(ProcedureAnesthesiaConsentChart chart) {
    return repository.save(chart);
  }

  @Override
  public ProcedureAnesthesiaConsentChart updateExistsChart(ProcedureAnesthesiaConsentChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<ProcedureAnesthesiaConsentChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<ProcedureAnesthesiaConsentChart> getAllCharts(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
