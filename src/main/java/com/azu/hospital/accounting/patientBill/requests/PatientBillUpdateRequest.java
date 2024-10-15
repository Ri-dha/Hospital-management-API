package com.azu.hospital.accounting.patientBill.requests;

import com.azu.hospital.utils.enums.accounting.PatientBillEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PatientBillUpdateRequest(
        String note,
        String place,

        Integer quantity,

        BigDecimal price,

        BigDecimal fullPrice,

        BigDecimal discount,

        PatientBillEnum type

) {
}
