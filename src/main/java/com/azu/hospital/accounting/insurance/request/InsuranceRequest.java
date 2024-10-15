package com.azu.hospital.accounting.insurance.request;

import com.azu.hospital.utils.enums.patient.BillState;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InsuranceRequest (

        @NotNull
        BigDecimal insuranceAmount,
        @NotNull
        BigDecimal depositAmount


){
}
