package com.azu.hospital.accounting.patient_new_wallet.dto;

public record PatientNewWalletDto (

        Long id ,
        Long operationId,
        Long userId,
        Long patientId,
        String patientName,
        String operationName,
        String userName,
        String note,
        Double operationCost,
        Double doctorPercentage

){
}
