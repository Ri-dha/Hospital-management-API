package com.azu.hospital.ph.lab_inventory.lab_main_table.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MainLabStoreDtoV2 {

    private Long id;
    private String tubeName;
    private String tubeCompany;
    private Integer quantity;
    private BigDecimal itemBuyingPrice;


    public MainLabStoreDtoV2(Long id, String tubeName, String tubeCompany, Integer quantity, BigDecimal itemBuyingPrice) {
        this.id = id;
        this.tubeName = tubeName;
        this.tubeCompany = tubeCompany;
        this.quantity = quantity;
        this.itemBuyingPrice = itemBuyingPrice;
    }
}
