package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class StoreHoseExpanseDto {

    private String tubeName ;

    private Integer tubeExpanseQuantity;

    private Instant createdAt;

    private Instant updatedAt;

    public StoreHoseExpanseDto() {
    }

    public StoreHoseExpanseDto(
            String tubeName,
            Integer tubeExpanseQuantity,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.tubeName = tubeName;
        this.tubeExpanseQuantity = tubeExpanseQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
