package com.azu.hospital.Patients.charts.postDischargeInstructions.dao;

import com.azu.hospital.Patients.charts.postDischargeInstructions.entity.PostDischargeInstructionsChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PostDischargeInstructionsJpaDataAccess")
public class PostDischargeInstructionsJpaDataAccess implements PostDischargeInstructionsDao {

  private final PostDischargeInstructionsJpaRepository repository;

  @Autowired
  public PostDischargeInstructionsJpaDataAccess(PostDischargeInstructionsJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public PostDischargeInstructionsChart createNewChart(PostDischargeInstructionsChart chart) {
    return repository.save(chart);
  }

  @Override
  public PostDischargeInstructionsChart updateExistsChart(PostDischargeInstructionsChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<PostDischargeInstructionsChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<PostDischargeInstructionsChart> getAllCharts(Long patientId) {
    return repository.findAllChartsByPatientId(patientId);
  }
}
