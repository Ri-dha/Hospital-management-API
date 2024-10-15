package com.azu.hospital.Patients.charts.discharge.dao;

import com.azu.hospital.Patients.charts.discharge.entity.DischargeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("DischargeJpaDataAccess")
public class DischargeJpaDataAccess implements DischargeDao{
  private final DischargeJpaRepository repository;

  @Autowired
  public DischargeJpaDataAccess(DischargeJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public DischargeChart createNewChart(DischargeChart chart) {
    return repository.save(chart);
  }

  @Override
  public DischargeChart updateExistsChart(DischargeChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<DischargeChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public Page<DischargeChart> getAllCharts(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<DischargeChart> getAllChartsByPatientId(Long patientId) {
    return repository.findAllByPatientId(patientId);
  }
}
