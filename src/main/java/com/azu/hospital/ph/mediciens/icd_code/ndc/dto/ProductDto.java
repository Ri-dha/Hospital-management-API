package com.azu.hospital.ph.mediciens.icd_code.ndc.dto;


public record ProductDto(
        Long id,
        String name,
        String productNDC
) {

    @Override
    public String name() {
        return name;
    }
}
