package com.azu.hospital.Patients.charts.nursingObservation.dao;


import com.azu.hospital.Patients.charts.nursingObservation.entity.NursingObservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("NursingObservationJpaData")
public class NursingObservationJpaDataAccess implements NursingObservationDao {

    private final NursingObservationRepository repository;


    @Autowired
    public NursingObservationJpaDataAccess(@Qualifier("nursingObservationRepository") NursingObservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createNewChart(NursingObservation chart) {
        repository.save(chart);
    }

    @Override
    public void updateExistsChart(NursingObservation update) {
        repository.save(update);
    }

    @Override
    public Optional<NursingObservation> findChartById(Long chartId) {
        return repository.findById(chartId);
    }

    @Override
    public List<NursingObservation> getAllCharts() {
        return repository.findAll();
    }

    @Override
    public List<NursingObservation> getAllChartsByPatientId(Long patientId) {
        return repository.getAllChartByPatientId(patientId);
    }
}
