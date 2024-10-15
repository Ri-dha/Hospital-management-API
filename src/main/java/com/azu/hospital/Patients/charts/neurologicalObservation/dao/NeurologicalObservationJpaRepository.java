package com.azu.hospital.Patients.charts.neurologicalObservation.dao;

import com.azu.hospital.Patients.charts.neurologicalObservation.entities.NeurologicalObservationChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface NeurologicalObservationJpaRepository extends JpaRepository<NeurologicalObservationChart, Long> {
  @Query("SELECT chart FROM NeurologicalObservationChart chart WHERE chart.patient.id = :patientId ORDER BY COALESCE(chart.updatedAt,chart.createdAt) DESC")
  List<NeurologicalObservationChart> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
