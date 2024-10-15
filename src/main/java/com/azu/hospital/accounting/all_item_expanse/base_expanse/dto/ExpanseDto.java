package com.azu.hospital.accounting.all_item_expanse.base_expanse.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExpanseDto {


    private Long id;

    private Long itemId;

    private String itemName;

    private Integer itemCount;

    private BigDecimal itemPrice;

    private BigDecimal totalPrice;


    public ExpanseDto() {
    }

    public ExpanseDto(Long id, Long itemId, String itemName, Integer itemCount, BigDecimal itemPrice, BigDecimal totalPrice) {
        this.id = id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;
    }

    public ExpanseDto(Long id, String drugName, Integer drugCount) {
        this.id = id;
        this.itemName = drugName;
        this.itemCount = drugCount;
    }
}
