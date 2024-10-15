package com.azu.hospital.Patients.charts.sedatedPatientAssessment.dao;

import com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities.SedatedPatientAssessment;

import java.util.List;
import java.util.Optional;


public interface SedatedPatientAssessmentDao {
  SedatedPatientAssessment createNewChart(SedatedPatientAssessment chart);

  SedatedPatientAssessment updateExistsChart(SedatedPatientAssessment update);

  Optional<SedatedPatientAssessment> findChartById(Long chartId);

  List<SedatedPatientAssessment> getAllCharts(Long patientId);
}
