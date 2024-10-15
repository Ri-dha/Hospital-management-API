package com.azu.hospital.Patients.charts.preAnestheticEvaluation.utils;


import com.azu.hospital.Patients.charts.preAnestheticEvaluation.request.PreAnestheticEvaluationChartRequest;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.entity.PreAnestheticEvaluationChart;

public class PreAnestheticEvaluationRequestToChart {
    public static PreAnestheticEvaluationChart getAnestheticEvaluationChart(PreAnestheticEvaluationChartRequest request){
        return new PreAnestheticEvaluationChart.Builder()
                .withProposedProcedure(request.getProposedProcedure())
                .withDate(request.getDate())
                .withTime(request.getTime())
                .withAnesthesiologist(request.getAnesthesiologist())
                .withAsaStatus(request.getAsa())
                .withLabsEcg(request.getLabsEcg())
                .withPhysicalExam(request.getPhysicalExam())
                .withPlan(request.getPlan()).build();
    }
}
