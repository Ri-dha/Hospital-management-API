package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dto;

import com.azu.hospital.utils.enums.patient.PatientTransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record PatientTransactionDto (

        Long id ,

        BigDecimal amount ,

        PatientTransactionType type ,

        Instant createdAt ,
        Instant updatedAt



        ){



}
