package com.azu.hospital.Patients.charts.neurologicalObservation.dao;

import com.azu.hospital.Patients.charts.neurologicalObservation.entities.NeurologicalObservationChart;

import java.util.List;
import java.util.Optional;

public interface NeurologicalObservationDao {
  NeurologicalObservationChart createNewChart(NeurologicalObservationChart chart);

  NeurologicalObservationChart updateExistsChart(NeurologicalObservationChart update);

  Optional<NeurologicalObservationChart> findChartById(Long chartId);

  List<NeurologicalObservationChart> getAllChartsByPatientId(Long patientId);
}
