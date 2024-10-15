package com.azu.hospital.Patients.charts.patientConsentAnesthesia.utils;

import com.azu.hospital.Patients.charts.patientConsentAnesthesia.request.PatientConsentAnesthesiaRequest;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.entity.PatientConsentAnesthesiaChart;

public class PatientConsentAnesthesiaRequestToChart {
  public static PatientConsentAnesthesiaChart requestToChart(PatientConsentAnesthesiaRequest request){
    return new PatientConsentAnesthesiaChart.Builder()
            .withAnesthesiaType(request.anesthesiaType())
            .withPatientSignature(request.patientSignature())
            .withPatientSignatureDate(request.patientSignatureDate())
            .withPatientSignatureTime(request.patientSignatureTime())
            .withWitnessSignature(request.witnessSignature())
            .withWitnessSignatureDate(request.witnessSignatureDate())
            .withWitnessSignatureTime(request.witnessSignatureTime())
            .withWitnessSignature(request.witnessSignature())
            .withAnesthesiaProviderSignature(request.anesthesiaProviderSignature())
            .withAnesthesiaProviderSignatureDate(request.anesthesiaProviderSignatureDate())
            .withAnesthesiaProviderSignatureTime(request.anesthesiaProviderSignatureTime())
            .build();
  }
}
