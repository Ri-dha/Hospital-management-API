package com.azu.hospital.Patients.charts.preOperativeHPChart.dao;

import com.azu.hospital.Patients.charts.preOperativeHPChart.entity.PreOperativeHPChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PreOperativeHpChartJpaDataAccess")
public class PreOperativeHpChartJpaDataAccess implements PreOperativeHPChartDao {

  private final PreOperativeHPChartRepository repository;

  @Autowired
  public PreOperativeHpChartJpaDataAccess(PreOperativeHPChartRepository repository) {
    this.repository = repository;
  }

  @Override
  public PreOperativeHPChart createNewChart(PreOperativeHPChart chart) {
    return repository.save(chart);
  }

  @Override
  public PreOperativeHPChart updateExistsChart(PreOperativeHPChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<PreOperativeHPChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<PreOperativeHPChart> getAllCharts(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
