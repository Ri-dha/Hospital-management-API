package com.azu.hospital.Patients.charts.allPatientCharts.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("AllPatientChartsJpaDataAccess")
public class AllPatientChartsJpaDataAccess implements AllPatientChartsDao {

  private final AllPatientChartsJpaRepository repository;

  @Autowired
  public AllPatientChartsJpaDataAccess(AllPatientChartsJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public AllPatientChart addNewChart(AllPatientChart chart) {
    return repository.save(chart);
  }

  @Override
  public void updateCharts(AllPatientChart chart) {
    repository.save(chart);

  }

  @Override
  public Optional<AllPatientChart> getAllPatientChartsByPatientId(Long chartId) {
    return repository.findChartsByPatientId(chartId);
  }
}
