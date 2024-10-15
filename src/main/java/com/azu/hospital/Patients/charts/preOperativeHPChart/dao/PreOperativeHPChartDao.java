package com.azu.hospital.Patients.charts.preOperativeHPChart.dao;

import com.azu.hospital.Patients.charts.preOperativeHPChart.entity.PreOperativeHPChart;

import java.util.List;
import java.util.Optional;

public interface PreOperativeHPChartDao {
  PreOperativeHPChart createNewChart(PreOperativeHPChart chart);

  PreOperativeHPChart updateExistsChart(PreOperativeHPChart update);

  Optional<PreOperativeHPChart> findChartById(Long chartId);

  List<PreOperativeHPChart> getAllCharts(Long patientId);
}
