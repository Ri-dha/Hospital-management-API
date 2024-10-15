package com.azu.hospital.Patients.charts.doctorTour.dao;

import com.azu.hospital.Patients.charts.doctorTour.entity.DoctorTourChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("DoctorTourJpaDataAccess")
public class DoctorTourJpaDataAccess implements DoctorTourDao {
  private final DoctorTourJpaRepository repository;

  @Autowired
  public DoctorTourJpaDataAccess(
          DoctorTourJpaRepository repository
  ) {
    this.repository = repository;
  }

  @Override
  public DoctorTourChart createNewChart(DoctorTourChart chart) {
    return repository.save(chart);
  }

  @Override
  public DoctorTourChart updateExistsChart(DoctorTourChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<DoctorTourChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<DoctorTourChart> getAllCharts() {
    return repository.findAll();
  }


  @Override
  public List<DoctorTourChart> getAllChartsByPatientId(Long patientId) {
    return repository.getAllChartByPatientId(patientId);
  }
}
