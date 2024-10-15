package com.azu.hospital.Patients.charts.preAnestheticEvaluation.dao;

import com.azu.hospital.Patients.charts.preAnestheticEvaluation.entity.PreAnestheticEvaluationChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository("AnestheticEvaluationChartDAO")
public class PrePreAnestheticEvaluationChartJpaDataAccess implements PreAnestheticEvaluationChartDao {

    private final PreAnestheticEvaluationChartRepository preAnestheticEvaluationChartRepository;

    @Autowired
    public PrePreAnestheticEvaluationChartJpaDataAccess(PreAnestheticEvaluationChartRepository preAnestheticEvaluationChartRepository) {
        this.preAnestheticEvaluationChartRepository = preAnestheticEvaluationChartRepository;
    }

    @Override
    public PreAnestheticEvaluationChart createNewChart(PreAnestheticEvaluationChart request) {
        return preAnestheticEvaluationChartRepository.save(request);
    }

    @Override
    public PreAnestheticEvaluationChart updateExistsChart(PreAnestheticEvaluationChart update) {
        return preAnestheticEvaluationChartRepository.save(update);
    }

    @Override
    public Optional<PreAnestheticEvaluationChart> findChartById(Long chartId) {
        return preAnestheticEvaluationChartRepository.findById(chartId);
    }

    @Override
    public List<PreAnestheticEvaluationChart> getAllCharts(Long patientId) {
        return preAnestheticEvaluationChartRepository.getAllChartsByPatientId(patientId);
    }
}
