package com.azu.hospital.lab_collection.test_list.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class LabListDto {

    private Long id;

    private String name;

    private BigDecimal iqdPrice;

    private BigDecimal usdPrice;

    public LabListDto() {
    }

    public LabListDto(Long id, String name , BigDecimal iqdPrice , BigDecimal usdPrice) {
        this.id = id;
        this.name = name;
        this.iqdPrice = iqdPrice;
        this.usdPrice = usdPrice;
    }
}
