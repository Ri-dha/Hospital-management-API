package com.azu.hospital.Patients.charts.sedatedPatientAssessment.utils;


import com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities.SedatedPatientAssessment;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.request.SedatedPatientAssessmentRequest;

public class SedatedPatientAssessmentRequestToChart {
  public static SedatedPatientAssessment requestToChart(SedatedPatientAssessmentRequest request){
    return new SedatedPatientAssessment.Builder()
            .withMedications(request.medications())
            .withPositiveProblems(request.positiveProblems())
            .withPreOpMeds(request.preOpMeds())
            .withAsa(request.asa())
            .withPreProcedure(request.preProcedure())
            .withPreAnesthesiaState(request.preAnesthesiaState())
            .withPatientSafety(request.patientSafety())
            .withEyeCare(request.eyeCare())
            .withAnestheticTechnique(request.anestheticTechnique())
            .withMonitorsEquipment(request.monitorsEquipment())
            .withAirwayManagement(request.airwayManagement())
            .withAnesthesiaStartTime(request.anesthesiaStartTime())
            .withAnesthesiaEndTime(request.anesthesiaEndTime())
            .withProcedureStartTime(request.procedureStartTime())
            .withProcedureEndTime(request.procedureEndTime())
            .build();
  }
}
