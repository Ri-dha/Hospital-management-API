package com.azu.hospital.Patients.charts.painManagment.dao;

import com.azu.hospital.Patients.charts.painManagment.entity.PainManagementChart;

import java.util.List;
import java.util.Optional;

public interface PainManagementDao {
  PainManagementChart createNewChart(PainManagementChart chart);

  PainManagementChart updateExistsChart(PainManagementChart update);

  Optional<PainManagementChart> findChartById(Long chartId);

  List<PainManagementChart> getAllChart(Long patientId);
}
