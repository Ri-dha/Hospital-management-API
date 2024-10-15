package com.azu.hospital.Patients.charts.preAdvanceDirective.dao;

import com.azu.hospital.Patients.charts.preAdvanceDirective.entity.PreAdvanceDirectiveChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PreAdvanceDirectiveJpaRepository
        extends JpaRepository<PreAdvanceDirectiveChart, Long> {
  @Query("SELECT chart FROM PreAdvanceDirectiveChart chart WHERE chart.patient.id = :patientId ORDER BY coalesce(chart.createdAt,chart.updatedAt)DESC")
  List<PreAdvanceDirectiveChart> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
