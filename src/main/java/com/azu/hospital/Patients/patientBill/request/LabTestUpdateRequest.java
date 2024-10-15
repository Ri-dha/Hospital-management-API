package com.azu.hospital.Patients.patientBill.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record LabTestUpdateRequest (
        @NotNull
        BigDecimal price
){
}
