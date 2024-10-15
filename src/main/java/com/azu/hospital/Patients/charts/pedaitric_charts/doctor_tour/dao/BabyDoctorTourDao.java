package com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.dao;

import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.entity.BabyDoctorTour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BabyDoctorTourDao {
    BabyDoctorTour createNewChart(BabyDoctorTour chart);

    BabyDoctorTour updateExistsChart(BabyDoctorTour update);

    Optional<BabyDoctorTour> findChartById(Long chartId);

    Page<BabyDoctorTour> getAllCharts( Pageable pageable);

    Page<BabyDoctorTour> getAllChartsByPatientId(Long patientId, Pageable pageable);
}
