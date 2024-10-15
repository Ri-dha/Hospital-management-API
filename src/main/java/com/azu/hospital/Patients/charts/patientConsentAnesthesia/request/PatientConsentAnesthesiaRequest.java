package com.azu.hospital.Patients.charts.patientConsentAnesthesia.request;


public record PatientConsentAnesthesiaRequest(
        String anesthesiaType,
        String patientSignature,
        String patientSignatureDate,
        String patientSignatureTime,
        String witnessSignature,
        String witnessSignatureDate,
        String witnessSignatureTime,
        String anesthesiaProviderSignature,
        String anesthesiaProviderSignatureDate,
        String anesthesiaProviderSignatureTime
) {
}
