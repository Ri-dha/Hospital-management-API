package com.azu.hospital.accounting.patientBill.requests;

import com.azu.hospital.utils.enums.accounting.PatientBillEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PatientBillCreateRequest(

        @NotNull(message = "Patient Field Required")
        Long patientId,

        String note,

        @NotNull(message = "Quantity Field Required")
        Integer quantity,

        @NotNull(message = "Price Field Required")
        BigDecimal price,

        @NotNull(message = "Full Price Field Required")
        BigDecimal fullPrice,

        @NotNull(message = "Place Field Required")
        String place,
        BigDecimal discount,

        @NotNull(message = "Type Field Required")
        @Enumerated
        PatientBillEnum type


){}
