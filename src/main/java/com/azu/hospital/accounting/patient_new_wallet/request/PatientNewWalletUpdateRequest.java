package com.azu.hospital.accounting.patient_new_wallet.request;

public record PatientNewWalletUpdateRequest (

        Long operationId,
        Long userId,
        Long patientId,
        String patientName,
        String operationName,
        String userName,
        String note,
        Double operationCost,
        Double doctorPercentage,
        Double hospitalPercentage

){
}
