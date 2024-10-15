package com.azu.hospital.Patients.patientDrugs.request;

public record PatientDrugRequest(
        String drug,
        String dose,
        String time,
        String roa,
        Integer count,
        String note
) {
}
