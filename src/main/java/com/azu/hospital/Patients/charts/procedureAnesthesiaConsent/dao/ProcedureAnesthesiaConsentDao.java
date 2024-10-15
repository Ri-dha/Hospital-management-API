package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dao;

import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.entity.ProcedureAnesthesiaConsentChart;

import java.util.List;
import java.util.Optional;

public interface ProcedureAnesthesiaConsentDao {
  ProcedureAnesthesiaConsentChart createNewChart(ProcedureAnesthesiaConsentChart chart);

  ProcedureAnesthesiaConsentChart updateExistsChart(ProcedureAnesthesiaConsentChart update);

  Optional<ProcedureAnesthesiaConsentChart> findChartById(Long chartId);

  List<ProcedureAnesthesiaConsentChart> getAllCharts(Long patientId);
}
