package com.azu.hospital.Patients.charts.neurologicalObservation.dao;

import com.azu.hospital.Patients.charts.neurologicalObservation.entities.NeurologicalObservationChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("NeurologicalObservationJpaDataAccess")
public class NeurologicalObservationJpaDataAccess implements NeurologicalObservationDao {

  private final NeurologicalObservationJpaRepository repository;

  @Autowired
  public NeurologicalObservationJpaDataAccess(NeurologicalObservationJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public NeurologicalObservationChart createNewChart(NeurologicalObservationChart chart) {
    return repository.save(chart);
  }

  @Override
  public NeurologicalObservationChart updateExistsChart(NeurologicalObservationChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<NeurologicalObservationChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<NeurologicalObservationChart> getAllChartsByPatientId(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
