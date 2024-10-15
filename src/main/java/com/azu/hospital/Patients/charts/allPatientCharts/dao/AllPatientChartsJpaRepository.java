package com.azu.hospital.Patients.charts.allPatientCharts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AllPatientChartsJpaRepository extends JpaRepository<AllPatientChart, Long> {
  @Query("SELECT chart FROM AllPatientChart chart WHERE chart.patient.id = :patientId")
  Optional<AllPatientChart> findChartsByPatientId(@Param("patientId") Long patientId);
}
