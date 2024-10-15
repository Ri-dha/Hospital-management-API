package com.azu.hospital.Patients.charts.fluidBalance.dao.repository;

import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidBalanceChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FluidBalanceJpaRepository extends JpaRepository<FluidBalanceChart, Long> {
  @Query("SELECT chart FROM FluidBalanceChart chart WHERE chart.patient.id = :patientId ORDER BY COALESCE(chart.updatedAt,chart.createdAt) DESC")
  List<FluidBalanceChart> findAllByPatientId(@Param("patientId") Long patientId);
}
