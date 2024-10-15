package com.azu.hospital.Patients.charts.patientConsentAnesthesia.dao;

import com.azu.hospital.Patients.charts.patientConsentAnesthesia.entity.PatientConsentAnesthesiaChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface PatientConsentAnesthesiaJpaRepository extends JpaRepository<PatientConsentAnesthesiaChart, Long> {
  @Query("SELECT chart FROM PatientConsentAnesthesiaChart chart WHERE chart.patient.id = :patientId")
  List<PatientConsentAnesthesiaChart> findAllChartsByPatientId(Long patientId);
}
