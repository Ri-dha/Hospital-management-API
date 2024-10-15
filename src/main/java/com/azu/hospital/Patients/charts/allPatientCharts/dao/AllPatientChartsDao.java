package com.azu.hospital.Patients.charts.allPatientCharts.dao;

import java.util.Optional;

public interface AllPatientChartsDao {
  AllPatientChart addNewChart(AllPatientChart chart);
  void updateCharts(AllPatientChart chart);

  Optional<AllPatientChart> getAllPatientChartsByPatientId(Long chartId);
}
