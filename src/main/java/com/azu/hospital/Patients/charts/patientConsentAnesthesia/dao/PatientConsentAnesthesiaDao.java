package com.azu.hospital.Patients.charts.patientConsentAnesthesia.dao;

import com.azu.hospital.Patients.charts.patientConsentAnesthesia.entity.PatientConsentAnesthesiaChart;

import java.util.List;
import java.util.Optional;

public interface PatientConsentAnesthesiaDao {
  PatientConsentAnesthesiaChart createNewChart(PatientConsentAnesthesiaChart chart);

  PatientConsentAnesthesiaChart updateExistsChart(PatientConsentAnesthesiaChart update);

  Optional<PatientConsentAnesthesiaChart> findChartById(Long chartId);

  List<PatientConsentAnesthesiaChart> getAllCharts(Long patientId);
}
