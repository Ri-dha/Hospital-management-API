package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dto;


import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.entity.StoreHoseExpanse;
import org.springframework.stereotype.Service;

@Service
public class StoreHoseExpanseDtoMapper {

    public StoreHoseExpanseDto toDto(StoreHoseExpanse storeHoseExpanse){
        return new StoreHoseExpanseDto(
                storeHoseExpanse.getMainLabTubeStore().getItemTubeName() ,
                storeHoseExpanse.getQuantity(),
                storeHoseExpanse.getCreatedAt(),
                storeHoseExpanse.getUpdatedAt()
        );
    }
}
