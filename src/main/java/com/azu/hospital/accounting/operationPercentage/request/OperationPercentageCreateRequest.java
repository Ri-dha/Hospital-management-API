package com.azu.hospital.accounting.operationPercentage.request;

import java.math.BigDecimal;

public record OperationPercentageCreateRequest (

        BigDecimal doctorPercentage,
        BigDecimal anesthetistDoctorPercentage,
        BigDecimal permanentPercentage,
        BigDecimal pharmacistPercentage,
        BigDecimal nursePercentage,
        BigDecimal anesthetistPercentage,
        BigDecimal hospitalPercentage

){
}
