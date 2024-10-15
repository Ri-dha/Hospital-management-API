package com.azu.hospital.accounting.operationPercentage.dto;

import com.azu.hospital.accounting.operationPercentage.entity.OperationPercentage;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class OperationPercentageDtoMapper implements Function<OperationPercentage, OperationPercentageDto> {
    @Override
    public OperationPercentageDto apply(OperationPercentage operationPercentage) {
        return new OperationPercentageDto(
                operationPercentage.getId(),
                operationPercentage.getSurgicalList().getId(),
                Double.parseDouble(String.valueOf(operationPercentage.getDoctorPercentage())),
                Double.parseDouble(String.valueOf(operationPercentage.getAnesthetistDoctorPercentage())),
               Double.parseDouble(String.valueOf(operationPercentage.getPermanentPercentage())),
               Double.parseDouble(String.valueOf(operationPercentage.getPharmacistPercentage())),
               Double.parseDouble(String.valueOf(operationPercentage.getNursePercentage())),
                Double.parseDouble(String.valueOf(operationPercentage.getAnesthetistPercentage())),
                Double.parseDouble(String.valueOf(operationPercentage.getHospitalPercentage()))
        );
    }
}
