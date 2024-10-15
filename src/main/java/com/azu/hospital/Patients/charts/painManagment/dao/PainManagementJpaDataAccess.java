package com.azu.hospital.Patients.charts.painManagment.dao;

import com.azu.hospital.Patients.charts.painManagment.entity.PainManagementChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PainManagementJpaDataAccess")
public class PainManagementJpaDataAccess implements PainManagementDao{

  private final PainManagementJpaRepository repository;

  @Autowired
  public PainManagementJpaDataAccess(PainManagementJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public PainManagementChart createNewChart(PainManagementChart chart) {
    return repository.save(chart);
  }

  @Override
  public PainManagementChart updateExistsChart(PainManagementChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<PainManagementChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<PainManagementChart> getAllChart(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
