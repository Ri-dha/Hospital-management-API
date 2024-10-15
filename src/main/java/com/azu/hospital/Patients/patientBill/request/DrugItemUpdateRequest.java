package com.azu.hospital.Patients.patientBill.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DrugItemUpdateRequest (
        @NotNull
        BigDecimal price
){
}
