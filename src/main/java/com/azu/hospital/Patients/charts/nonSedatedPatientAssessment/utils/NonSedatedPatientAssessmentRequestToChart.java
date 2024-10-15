package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.utils;


import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.NonSedatedPatientAssessment;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.request.NonSedatedPatientAssessmentRequest;

public class NonSedatedPatientAssessmentRequestToChart {
  public static NonSedatedPatientAssessment requestToChart(NonSedatedPatientAssessmentRequest request){
    return new NonSedatedPatientAssessment.Builder()
            .withPatientPositions(request.patientPositions())
            .withPatientLimitation(request.patientLimitation())
            .withProcedureArea(request.procedureArea())
            .withMedsPerMD(request.medsPerMD())
            .withMedsPerMDListOther(request.medsPerMDListOther())
            .withNursesNotes(request.nursesNotes())
            .withPostOperativeDiagnosis(request.postOperativeDiagnosis())
            .withProcedurePerformed(request.procedurePerformed())
            .withProcedurePerformed(request.procedurePerformed())
            .withTimeStart(request.timeStart())
            .withTimeFinish(request.timeFinish())
            .build();

  }
}
