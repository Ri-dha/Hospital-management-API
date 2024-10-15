package com.azu.hospital.Patients.charts.postAnestheticEvaluation.dao;

import com.azu.hospital.Patients.charts.postAnestheticEvaluation.entity.PostAnestheticEvaluationChart;

import java.util.List;
import java.util.Optional;

public interface PostAnestheticEvaluationChartDao {
    PostAnestheticEvaluationChart createNewChart(PostAnestheticEvaluationChart request);

    PostAnestheticEvaluationChart updateExistsChart(PostAnestheticEvaluationChart update);

    Optional<PostAnestheticEvaluationChart> findChartById(Long chartId);

    List<PostAnestheticEvaluationChart> getAllPostAnestheticEvaluationChartByPatientId(Long patientId);
}
