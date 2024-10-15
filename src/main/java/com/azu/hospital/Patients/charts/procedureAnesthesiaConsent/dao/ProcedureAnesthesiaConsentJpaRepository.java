package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dao;

import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.entity.ProcedureAnesthesiaConsentChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface ProcedureAnesthesiaConsentJpaRepository extends JpaRepository<ProcedureAnesthesiaConsentChart, Long> {
  @Query("SELECT chart FROM ProcedureAnesthesiaConsentChart chart WHERE chart.patient.id = :patientId ORDER BY coalesce(chart.createdAt,chart.updatedAt)DESC")
  List<ProcedureAnesthesiaConsentChart> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
