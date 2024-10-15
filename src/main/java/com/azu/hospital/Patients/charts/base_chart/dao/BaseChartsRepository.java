package com.azu.hospital.Patients.charts.base_chart.dao;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.doctorTour.entity.DoctorTourChart;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Transactional
public interface BaseChartsRepository extends JpaRepository<BaseCharts, Long> {

    @Query("SELECT bc FROM BaseCharts bc WHERE bc.patient.id = :patientId order by coalesce(bc.createdAt,bc.updatedAt) DESC ")
    Page<BaseCharts> findAllByPatientIdWithCoalesceOrderBy(@Param("patientId") Long patientId, Pageable pageable);





}
