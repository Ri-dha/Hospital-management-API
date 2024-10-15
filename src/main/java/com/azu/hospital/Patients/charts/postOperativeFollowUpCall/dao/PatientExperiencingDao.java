package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;

import java.util.List;
import java.util.Optional;

public interface PatientExperiencingDao {
  void createNewExperiencingChart(List<PatientExperiencing> chart);

  PatientExperiencing updateExistsExperiencingChart(PatientExperiencing update);

  Optional<PatientExperiencing> findExperiencingChartById(Long patientChartId);

  List<PatientExperiencing> getAllExperiencingCharts(Long chartId);


}
