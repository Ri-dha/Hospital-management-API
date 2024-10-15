package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DrugRequestHandlerSumDto {

    private Long drugId;

    private Integer totalQuantity;

    private String drugName;

    private BigDecimal price;

    private BigDecimal totalPrices;

    public DrugRequestHandlerSumDto(Long drugId, Integer totalQuantity, String drugName, BigDecimal price, BigDecimal totalPrices) {
        this.drugId = drugId;
        this.totalQuantity = totalQuantity;
        this.drugName = drugName;
        this.price = price;
        this.totalPrices = totalPrices;
    }
}
