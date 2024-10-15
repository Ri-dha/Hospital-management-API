package com.azu.hospital.Patients.charts.patientAssessment.dao;

import com.azu.hospital.Patients.charts.patientAssessment.entity.PatientAssessmentChart;

import java.util.List;
import java.util.Optional;

public interface PatientAssessmentChartDao {
    PatientAssessmentChart createNewChart(PatientAssessmentChart chart);

    Optional<PatientAssessmentChart> findChartById(Long chartId);

    PatientAssessmentChart updateExistsChart(PatientAssessmentChart update);

    List<PatientAssessmentChart> getAllCharts(Long patientId);
}
