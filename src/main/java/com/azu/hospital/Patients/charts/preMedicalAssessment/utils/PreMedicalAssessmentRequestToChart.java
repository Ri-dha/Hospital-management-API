package com.azu.hospital.Patients.charts.preMedicalAssessment.utils;

import com.azu.hospital.Patients.charts.preMedicalAssessment.request.PreMedicalAssessmentRequest;
import com.azu.hospital.Patients.charts.preMedicalAssessment.entity.PreMedicalAssessmentChart;


public class PreMedicalAssessmentRequestToChart {
  public static PreMedicalAssessmentChart requestToChart(PreMedicalAssessmentRequest request){
    return new PreMedicalAssessmentChart.Builder()
            .withPlannedSurgicalProcedure(request.plannedSurgicalProcedure())
            .withSurgeryDate(request.surgeryDate())
            .withSurgeryLocation(request.surgeryLocation())
            .withAttendingSurgeon(request.attendingSurgeon())
            .withPresentIllnessHistory(request.presentIllnessHistory())
            .withPastMedicalHistoryNote(request.pastMedicalHistoryNote())
            .withPastMedicalHistoryTable(request.pastMedicalHistoryTable())
            .withPriorAnesthesiaComplication(request.priorAnesthesiaComplication())
            .withSocialHistory(request.socialHistory())
            .withCardiacHistory(request.cardiacHistory())
            .withSymptomsSystem(request.symptomsSystem())
            .withBmi(request.bmi())
            .withNormalExamChecks(request.normalExamCheck())
            .withSurgicalRisk(request.surgicalRisk())
            .withCardiacRiskAssessment(request.cardiacRiskAssessment())
            .build();
  }
}
