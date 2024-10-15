package com.azu.hospital.Patients.charts.postAnestheticEvaluation.utils;


import com.azu.hospital.Patients.charts.postAnestheticEvaluation.request.PostAnestheticEvaluationRequest;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.entity.PostAnestheticEvaluationChart;

public class PostAnestheticEvaluationRequestToChart {
    public static PostAnestheticEvaluationChart getAnestheticEvaluationChart(PostAnestheticEvaluationRequest request){
        return new PostAnestheticEvaluationChart
                .Builder()
                .withAnesthesiaComplications(request.anesthesiaComplications())
                .withPatientStatus(request.patientStatus())
                .withPatientHas(request.patientHas())
                .withPatientHasOther(request.patientHasOther())
                .withPostAnesthesiaNote(request.postAnesthesiaNote())
                .withAnesthesiologist(request.anesthesiologist())
                .withDate(request.date())
                .withTime(request.time())
                .build();
    }
}
