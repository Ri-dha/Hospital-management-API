package com.azu.hospital.Patients.charts.painManagment.dao;

import com.azu.hospital.Patients.charts.painManagment.entity.PainManagementChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PainManagementJpaRepository extends JpaRepository<PainManagementChart, Long> {
  @Query("SELECT chart FROM PainManagementChart chart WHERE chart.patient.id = :patientId")
  List<PainManagementChart> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
