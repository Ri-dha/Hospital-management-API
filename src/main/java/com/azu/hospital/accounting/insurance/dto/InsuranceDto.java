package com.azu.hospital.accounting.insurance.dto;

import com.azu.hospital.utils.enums.patient.BillState;

import java.math.BigDecimal;

public record InsuranceDto (
        Long id,
        BigDecimal insuranceAmount,
        BigDecimal depositAmount

){
}
