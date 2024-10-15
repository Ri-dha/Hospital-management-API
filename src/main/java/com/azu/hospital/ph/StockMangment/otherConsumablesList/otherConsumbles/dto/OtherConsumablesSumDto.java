package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OtherConsumablesSumDto {

    @NotNull
    private Long otherConsumablesId;
    @NotNull
    private Integer totalQuantity;

    private String otherConsumablesName;

    private BigDecimal price;

    private BigDecimal totalPrices;


    public OtherConsumablesSumDto(Long otherConsumablesId, Integer totalQuantity, String otherConsumablesName, BigDecimal price, BigDecimal totalPrices) {
        this.otherConsumablesId = otherConsumablesId;
        this.totalQuantity = totalQuantity;
        this.otherConsumablesName = otherConsumablesName;
        this.price = price;
        this.totalPrices = totalPrices;
    }
}
