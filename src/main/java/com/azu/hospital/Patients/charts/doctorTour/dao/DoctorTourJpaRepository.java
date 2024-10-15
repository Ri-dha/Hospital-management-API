package com.azu.hospital.Patients.charts.doctorTour.dao;

import com.azu.hospital.Patients.charts.doctorTour.entity.DoctorTourChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DoctorTourJpaRepository extends JpaRepository<DoctorTourChart, Long> {
  @Query("SELECT chart FROM DoctorTourChart chart WHERE chart.patient.id = :patientId ORDER BY chart.createdAt ASC")
  List<DoctorTourChart> getAllChartByPatientId(@Param("patientId") Long patientId);

  @Override
  @Query("SELECT chart FROM DoctorTourChart chart WHERE chart.id = :chartId")
  Optional<DoctorTourChart> findById(@Param("chartId") Long chartId);
}
