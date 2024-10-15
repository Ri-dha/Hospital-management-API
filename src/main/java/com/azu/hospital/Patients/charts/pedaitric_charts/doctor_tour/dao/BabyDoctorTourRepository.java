package com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.dao;

import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.entity.BabyDoctorTour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BabyDoctorTourRepository extends JpaRepository<BabyDoctorTour, Long> {

    @Query("SELECT chart FROM BabyDoctorTour chart WHERE chart.prematureBaby.id = :patientId ORDER BY chart.createdAt ASC")
    Page<BabyDoctorTour> getAllChartByPatientId(Long patientId, Pageable pageable);

}
