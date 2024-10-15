package com.azu.hospital.ph.StockMangment.Consumbles.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConsumableForBillDto {

    private Long consumableId;
    private String consumableName;
    private Integer quantity;
    private BigDecimal price;
    private String bounce;

    public ConsumableForBillDto(Long consumableId, String consumableName, Integer quantity, BigDecimal price, String bounce) {
        this.consumableId = consumableId;
        this.consumableName = consumableName;
        this.quantity = quantity;
        this.price = price;
        this.bounce = bounce;
    }
}
