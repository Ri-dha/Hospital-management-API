package com.azu.hospital.Patients.charts.discharge.dao;

import com.azu.hospital.Patients.charts.discharge.entity.DischargeChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface DischargeJpaRepository extends JpaRepository<DischargeChart, Long> {
  @Query("SELECT chart FROM DischargeChart chart WHERE chart.patient.id = :patientId ORDER BY chart.createdAt ASC ")
  List<DischargeChart> findAllByPatientId(@Param("patientId") Long patientId);
}
