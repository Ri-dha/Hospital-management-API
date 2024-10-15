package com.azu.hospital.Patients.charts.nusringCarePlan.utils;

import com.azu.hospital.Patients.charts.nusringCarePlan.entity.NursingCarePlan;
import com.azu.hospital.Patients.charts.nusringCarePlan.request.NursingCarePlanRequest;

public class NursingCarePlanRequestToChart {
  public static NursingCarePlan getNursingCarePlan(NursingCarePlanRequest request){
    return new NursingCarePlan.Builder()
            .withNursingDiagnosis(request.getNursingDiagnosis())
            .withGoalsAndOutComes(request.getGoalsAndOutComes())
            .withIntervensions(request.getIntervensions())
            .withEvaluation(request.getEvaluation())
            .build();
  }
}
