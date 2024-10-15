package com.azu.hospital.Patients.patientBill.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OperationUpdateRequest(
        String name,
        @NotNull
        BigDecimal price,


        String note

){
}
