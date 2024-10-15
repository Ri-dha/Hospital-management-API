package com.azu.hospital.accounting.patient_wallet.request;


import java.math.BigDecimal;

public record PatientWalletRequest (
        Long patientId,

        BigDecimal amount

){

}
