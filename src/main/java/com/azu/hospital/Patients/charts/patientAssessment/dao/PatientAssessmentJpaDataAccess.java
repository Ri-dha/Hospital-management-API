package com.azu.hospital.Patients.charts.patientAssessment.dao;

import com.azu.hospital.Patients.charts.patientAssessment.entity.PatientAssessmentChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PatientAssessmentJpaDataAccess")
public class PatientAssessmentJpaDataAccess implements PatientAssessmentChartDao {

    private final PatientAssessmentJpaRepository repository;

    @Autowired
    public PatientAssessmentJpaDataAccess(PatientAssessmentJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public PatientAssessmentChart createNewChart(PatientAssessmentChart chart) {
        return repository.save(chart);
    }

    @Override
    public Optional<PatientAssessmentChart> findChartById(Long chartId) {
        return repository.findById(chartId);
    }

    @Override
    public PatientAssessmentChart updateExistsChart(PatientAssessmentChart update) {
        return repository.save(update);
    }

    @Override
    public List<PatientAssessmentChart> getAllCharts(Long patientId) {
        return repository.findAllChartsByPatientId(patientId);
    }
}
