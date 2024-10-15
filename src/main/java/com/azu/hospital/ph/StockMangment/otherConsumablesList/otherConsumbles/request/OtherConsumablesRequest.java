package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.request;

import java.math.BigDecimal;

public record OtherConsumablesRequest (

        Long id,
        String name,
        Integer count,
        String note,
        BigDecimal price

){
}
