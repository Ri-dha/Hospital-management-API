package com.azu.hospital.Patients.charts.discharge.utils;

import com.azu.hospital.Patients.charts.discharge.entity.DischargeChart;
import com.azu.hospital.Patients.charts.discharge.request.DischargeRequest;

public class DischargeRequestToChart {
  public static DischargeChart requestToChart(DischargeRequest request){
    return new DischargeChart.Builder()
            .withDischargedTime(request.dischargeTime())
            .withRecommendation(request.recommendation())
            .withDischargedDate(request.dischargedDate())
            .withTreatmentOnDischarge(request.treatmentOnDischarge())
            .withSurgicalMedicalProcedure(request.surgicalMedicalProcedure())
            .build();
  }
}
