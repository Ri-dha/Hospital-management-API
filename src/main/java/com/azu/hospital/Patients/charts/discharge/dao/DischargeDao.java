package com.azu.hospital.Patients.charts.discharge.dao;

import com.azu.hospital.Patients.charts.discharge.entity.DischargeChart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DischargeDao {
  DischargeChart createNewChart(DischargeChart chart);

  DischargeChart updateExistsChart(DischargeChart update);

  Optional<DischargeChart> findChartById(Long chartId);

  Page<DischargeChart> getAllCharts(Pageable pageable);

  List<DischargeChart> getAllChartsByPatientId(Long patientId);
}
