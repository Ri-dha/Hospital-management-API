package com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.dao;


import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.entity.BabyDoctorTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("BabyDoctorTourRepo")
public class BabyDoctorTourDataAccessJpa implements BabyDoctorTourDao {

    private final BabyDoctorTourRepository repository;

    @Autowired
    public BabyDoctorTourDataAccessJpa(@Qualifier("babyDoctorTourRepository") BabyDoctorTourRepository babyDoctorTourRepository) {
        this.repository = babyDoctorTourRepository;
    }


    @Override
    public BabyDoctorTour createNewChart(BabyDoctorTour chart) {
        return repository.save(chart);
    }

    @Override
    public BabyDoctorTour updateExistsChart(BabyDoctorTour update) {
        return repository.save(update);
    }

    @Override
    public Optional<BabyDoctorTour> findChartById(Long chartId) {
        return repository.findById(chartId);
    }

    @Override
    public Page<BabyDoctorTour> getAllCharts( Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<BabyDoctorTour> getAllChartsByPatientId(Long patientId, Pageable pageable) {
        return repository.getAllChartByPatientId(patientId,pageable);
    }
}
