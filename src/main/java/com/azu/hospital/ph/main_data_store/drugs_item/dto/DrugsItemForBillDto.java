package com.azu.hospital.ph.main_data_store.drugs_item.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class DrugsItemForBillDto {
    private Long drugId;
    private String drugTradeName;
    private Integer quantity;
    private String drugBonus;
    private BigDecimal price;


    public DrugsItemForBillDto(Long drugId, String drugTradeName, Integer quantity, String drugBonus, BigDecimal price) {
        this.drugId = drugId;
        this.drugTradeName = drugTradeName;
        this.quantity = quantity;
        this.drugBonus = drugBonus;
        this.price = price;
    }
}
