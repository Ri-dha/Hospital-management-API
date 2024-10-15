package com.azu.hospital.Patients.charts.preOperativeHPChart.dao;


import com.azu.hospital.Patients.charts.preOperativeHPChart.entity.PreOperativeHPChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PreOperativeHPChartRepository extends JpaRepository<PreOperativeHPChart, Long> {
  @Query("SELECT chart FROM PreOperativeHPChart chart WHERE chart.patient.id = :patientId")
  List<PreOperativeHPChart> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
