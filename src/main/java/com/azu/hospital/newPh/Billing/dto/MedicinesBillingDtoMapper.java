package com.azu.hospital.newPh.Billing.dto;

import com.azu.hospital.newPh.Billing.entity.MedicinesBilling;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MedicinesBillingDtoMapper implements Function<MedicinesBilling, MedicinesBillingDto>{
    @Override
    public MedicinesBillingDto apply(MedicinesBilling medicinesBilling) {
        return new MedicinesBillingDto(
                medicinesBilling.getId(),
                medicinesBilling.getBillingType().name(),
                medicinesBilling.getFinalPrice(),
                medicinesBilling.getQuantity(),
                medicinesBilling.getTotalItems(),
                medicinesBilling.getMedicinesRequestsList(),
                medicinesBilling.getPatient().getId() == null ? null : medicinesBilling.getPatient().getId(),
                medicinesBilling.getPatient().getPatientData().getFullName() == null ? null : medicinesBilling.getPatient().getPatientData().getFullName(),
                medicinesBilling.getCreateAt().toString(),
                medicinesBilling.getUpdateAt().toString()
        );
    }
}
