package com.azu.hospital.accounting.patient_wallet.dto;


import java.math.BigDecimal;

public record PatientWalletDto(

        Long id,
        BigDecimal balance

) {}