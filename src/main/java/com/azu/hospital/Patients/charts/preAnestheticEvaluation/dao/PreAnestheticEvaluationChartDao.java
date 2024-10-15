package com.azu.hospital.Patients.charts.preAnestheticEvaluation.dao;

import com.azu.hospital.Patients.charts.preAnestheticEvaluation.entity.PreAnestheticEvaluationChart;

import java.util.List;
import java.util.Optional;

public interface PreAnestheticEvaluationChartDao {

    PreAnestheticEvaluationChart createNewChart(PreAnestheticEvaluationChart request);
    PreAnestheticEvaluationChart updateExistsChart(PreAnestheticEvaluationChart update);
    Optional<PreAnestheticEvaluationChart> findChartById(Long chartId);
    List<PreAnestheticEvaluationChart> getAllCharts(Long patientId);
}
