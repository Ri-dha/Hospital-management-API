package com.azu.hospital.accounting.operationPercentage.dto;

public record OperationPercentageDto (

        Long id,
        Long operationId,
        Double doctorPercentage,
        Double anesthetistDoctorPercentage,
        Double permanentPercentage,
        Double pharmacistPercentage,
        Double nursePercentage,
        Double anesthetistPercentage,
        Double hospitalPercentage
){
}
