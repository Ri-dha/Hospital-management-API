package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.request;

import com.azu.hospital.utils.enums.patient.PatientTransactionType;

import java.math.BigDecimal;

public record PatientTransactionRequest(

        Long patientWalletId ,
        BigDecimal amount ,
        PatientTransactionType type
) {
}
