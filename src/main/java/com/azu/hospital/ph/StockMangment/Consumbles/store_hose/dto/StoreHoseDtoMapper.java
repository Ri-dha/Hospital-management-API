package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dto;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.entity.StoreHose;
import org.springframework.stereotype.Service;

@Service
public class StoreHoseDtoMapper {

    public StoreHoseDto toDto(StoreHose storeHose){
        return new StoreHoseDto(
                storeHose.getId() ,
                storeHose.getItem().getItemTubeName(),
                storeHose.getCount(),
                storeHose.getNote(),
                storeHose.getReceivedNote(),
                storeHose.getState()
        );
    }
}
