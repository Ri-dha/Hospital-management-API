package com.azu.hospital.Patients.charts.burnDiagram.dao;

import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnDiagramChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("BurnDiagramJpaDataAccess")
public class BurnDiagramJpaDataAccess implements BurnDiagramDao{

  private final BurnDiagramJpaRepository repository;

  @Autowired
  public BurnDiagramJpaDataAccess(BurnDiagramJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public BurnDiagramChart createNewChart(BurnDiagramChart chart) {
    return repository.save(chart);
  }

  @Override
  public BurnDiagramChart updateExistsChart(BurnDiagramChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<BurnDiagramChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<BurnDiagramChart> getAllChartsByPatientId(Long patientId) {
    return repository.getAllBurnDiagramByPatientId(patientId);
  }
}
