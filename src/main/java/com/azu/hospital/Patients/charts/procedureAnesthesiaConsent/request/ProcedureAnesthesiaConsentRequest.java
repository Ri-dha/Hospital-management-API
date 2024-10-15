package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record ProcedureAnesthesiaConsentRequest(
        String dateOfVisit,
        Long mrn,
        String proposedProcedures,
        String notEatenOrDrankSinceTime,
        String notEatenOrDrankSinceDate,
        String patientSignature,
        String patientSignatureDate,
        String witnessSignature,
        String witnessSignatureDate,
        String physicianSignature,
        String physicianSignatureDate
) {
}
