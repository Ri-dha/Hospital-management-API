package com.azu.hospital.Patients.charts.burnDiagram.dao;

import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnDiagramChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface BurnDiagramJpaRepository extends JpaRepository<BurnDiagramChart, Long> {
  @Query("SELECT chart FROM BurnDiagramChart chart WHERE chart.patient.id = :patientId ORDER BY COALESCE(chart.updatedAt,chart.createdAt) DESC")
  List<BurnDiagramChart> getAllBurnDiagramByPatientId(@Param("patientId") Long patientId);

}
