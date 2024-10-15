package com.azu.hospital.ph.lab_inventory.lab_main_table.dto;

import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MainLabStoreDtoV2Mapper implements Function<MainLabTubeStore, MainLabStoreDtoV2> {
    @Override
    public MainLabStoreDtoV2 apply(MainLabTubeStore mainLabTubeStore) {
        return new MainLabStoreDtoV2(
                mainLabTubeStore.getId(),
                mainLabTubeStore.getItemTubeName(),
                mainLabTubeStore.getItemTubeCompany(),
                mainLabTubeStore.getItemTubeQuantity(),
                mainLabTubeStore.getItemBuyingPrice()
        );
    }

}

