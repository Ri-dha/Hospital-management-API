package com.azu.hospital.Patients.charts.preAdvanceDirective.dao;

import com.azu.hospital.Patients.charts.preAdvanceDirective.entity.PreAdvanceDirectiveChart;

import java.util.List;
import java.util.Optional;

public interface PreAdvanceDirectiveDao {
  PreAdvanceDirectiveChart createNewChart(PreAdvanceDirectiveChart chart);

  PreAdvanceDirectiveChart updateExistsChart(PreAdvanceDirectiveChart update);

  Optional<PreAdvanceDirectiveChart> findChartById(Long chartId);

  List<PreAdvanceDirectiveChart> getAllCharts(Long patientId);
}
