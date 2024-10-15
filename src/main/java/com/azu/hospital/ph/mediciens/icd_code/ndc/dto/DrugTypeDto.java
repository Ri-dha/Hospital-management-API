package com.azu.hospital.ph.mediciens.icd_code.ndc.dto;

public record   DrugTypeDto(
        String drugType
) {

    @Override
    public String drugType() {
        return drugType;
    }
}
