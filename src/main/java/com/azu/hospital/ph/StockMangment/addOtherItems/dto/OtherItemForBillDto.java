package com.azu.hospital.ph.StockMangment.addOtherItems.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OtherItemForBillDto {

    private Long itemId;
    private String itemName;
    private Integer itemQuantity;
    private String bounce;
    BigDecimal itemBuyingPrice;

    public OtherItemForBillDto(Long itemId, String itemName, Integer itemQuantity, String bounce, BigDecimal itemBuyingPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.bounce = bounce;
        this.itemBuyingPrice = itemBuyingPrice;
    }
}
