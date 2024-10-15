package com.azu.hospital.ph.StockMangment.Consumbles.services;


import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.ph.utils.pagination.Pagination;
import lombok.Data;

@Data
public class ConsumablesResponse {
    private String message;
    private boolean status;
    private int statusCode;
    private Pagination<Consumables> data;
}
