package com.azu.hospital.Patients.charts.preAdvanceDirective.dao;

import com.azu.hospital.Patients.charts.preAdvanceDirective.entity.PreAdvanceDirectiveChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PreAdvanceDirectiveJpaDataAccess")
public class PreAdvanceDirectiveJpaDataAccess implements PreAdvanceDirectiveDao{
  private final PreAdvanceDirectiveJpaRepository repository;

  @Autowired
  public PreAdvanceDirectiveJpaDataAccess(PreAdvanceDirectiveJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public PreAdvanceDirectiveChart createNewChart(PreAdvanceDirectiveChart chart) {
    return repository.save(chart);
  }

  @Override
  public PreAdvanceDirectiveChart updateExistsChart(PreAdvanceDirectiveChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<PreAdvanceDirectiveChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<PreAdvanceDirectiveChart> getAllCharts(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
