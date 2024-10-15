package com.azu.hospital.Patients.charts.postAnestheticEvaluation.dao;

import com.azu.hospital.Patients.charts.postAnestheticEvaluation.entity.PostAnestheticEvaluationChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PostAnesthChartJpa")
public class PostAnestheticEvaluationChartJpaDataAccess implements PostAnestheticEvaluationChartDao{

    private final PostAnestheticEvaluationChartJpaRepository repository;

    @Autowired
    public PostAnestheticEvaluationChartJpaDataAccess(PostAnestheticEvaluationChartJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public PostAnestheticEvaluationChart createNewChart(PostAnestheticEvaluationChart request) {
        return repository.save(request);
    }

    @Override
    public PostAnestheticEvaluationChart updateExistsChart(PostAnestheticEvaluationChart update) {
        return repository.save(update);
    }

    @Override
    public Optional<PostAnestheticEvaluationChart> findChartById(Long chartId) {
        return repository.findById(chartId);
    }

    @Override
    public List<PostAnestheticEvaluationChart> getAllPostAnestheticEvaluationChartByPatientId(Long patientId){
        return repository.getAllPostAnestheticEvaluationChartByPatientId(patientId);
    }
}
