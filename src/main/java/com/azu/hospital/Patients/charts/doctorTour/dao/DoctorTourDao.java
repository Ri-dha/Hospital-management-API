package com.azu.hospital.Patients.charts.doctorTour.dao;

import com.azu.hospital.Patients.charts.doctorTour.entity.DoctorTourChart;

import java.util.List;
import java.util.Optional;

public interface DoctorTourDao {
  DoctorTourChart createNewChart(DoctorTourChart chart);

  DoctorTourChart updateExistsChart(DoctorTourChart update);

  Optional<DoctorTourChart> findChartById(Long chartId);

  List<DoctorTourChart> getAllCharts();

  List<DoctorTourChart> getAllChartsByPatientId(Long patientId);
}
