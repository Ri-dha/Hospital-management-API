package com.azu.hospital.Patients.charts.nusringCarePlan.dao;

import com.azu.hospital.Patients.charts.nusringCarePlan.entity.NursingCarePlan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("NursingCarePlanJpa")
public class NursingCarePlanJpaDataAccess implements NursingCarePlanDao{

  private final NursingCarePlanRepository repository;

  public NursingCarePlanJpaDataAccess(NursingCarePlanRepository repository) {
    this.repository = repository;
  }

  @Override
  public NursingCarePlan createNewChart(NursingCarePlan nursingCarePlan) {
    return repository.save(nursingCarePlan);
  }

  @Override
  public NursingCarePlan updateExistChart(NursingCarePlan nursingCarePlan) {
    return repository.save(nursingCarePlan);
  }

  @Override
  public Optional<NursingCarePlan> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<NursingCarePlan> getAllCharts(Long patientId) {
    return repository.findAllByPatientId(patientId);
  }
}
