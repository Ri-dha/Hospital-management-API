package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.utils;

import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.entity.ProcedureAnesthesiaConsentChart;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.request.ProcedureAnesthesiaConsentRequest;

public class ProcedureAnesthesiaConsentRequestToChart {
  public static ProcedureAnesthesiaConsentChart requestToChart(ProcedureAnesthesiaConsentRequest request){
    return new ProcedureAnesthesiaConsentChart.Builder()
            .withDateOfVisit(request.dateOfVisit())
            .withMrn(request.mrn())
            .withProposedProcedures(request.proposedProcedures())
            .withNotEatenOrDrankSinceTime(request.notEatenOrDrankSinceTime())
            .withNotEatenOrDrankSinceDate(request.notEatenOrDrankSinceDate())
            .withPatientSignature(request.patientSignature())
            .withPatientSignatureDate(request.patientSignatureDate())
            .withWitnessSignature(request.witnessSignature())
            .withWitnessSignatureDate(request.witnessSignatureDate())
            .withPhysicianSignature(request.physicianSignature())
            .withPhysicianSignatureDate(request.physicianSignatureDate())
            .build();
  }
}
