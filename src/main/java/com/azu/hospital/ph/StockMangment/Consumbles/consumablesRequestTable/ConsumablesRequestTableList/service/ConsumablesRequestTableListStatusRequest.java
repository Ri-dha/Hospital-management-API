package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumablesRequestTableListStatusRequest {
    private Long id;


    public ConsumablesRequestTableListStatusRequest() {
    }

    public ConsumablesRequestTableListStatusRequest(Long id) {
        this.id = id;
    }
}
