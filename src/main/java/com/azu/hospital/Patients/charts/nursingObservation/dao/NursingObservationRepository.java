package com.azu.hospital.Patients.charts.nursingObservation.dao;

import com.azu.hospital.Patients.charts.nursingObservation.entity.NursingObservation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Transactional
public interface NursingObservationRepository extends JpaRepository<NursingObservation, Long> {

    @Query("SELECT chart FROM NursingObservation chart WHERE chart.patient.id = :patientId ORDER BY COALESCE(chart.updatedAt,chart.createdAt) DESC")
    List<NursingObservation> getAllChartByPatientId(@Param("patientId") Long patientId);




}
