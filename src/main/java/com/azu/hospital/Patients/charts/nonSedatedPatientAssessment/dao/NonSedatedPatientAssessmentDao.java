package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao;

import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.NonSedatedPatientAssessment;

import java.util.List;
import java.util.Optional;

public interface NonSedatedPatientAssessmentDao {
  NonSedatedPatientAssessment createNewChart(NonSedatedPatientAssessment chart);

  NonSedatedPatientAssessment updateExistsChart(NonSedatedPatientAssessment update);

  Optional<NonSedatedPatientAssessment> findChartById(Long chartId);

  List<NonSedatedPatientAssessment> getAllCharts(Long patientId);
}
