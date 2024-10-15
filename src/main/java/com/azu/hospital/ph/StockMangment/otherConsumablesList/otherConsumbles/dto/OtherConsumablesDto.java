package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dto;

import java.math.BigDecimal;

public record OtherConsumablesDto (


        Long id,
        String name,
        Integer count,
        String note,

        BigDecimal price


){
}
