package com.azu.hospital.ph.lab_inventory.lab_main_table.dto;

import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class MainLabTubeStoreDtoMapper implements Function<MainLabTubeStore, MainLabTubeStoreDto> {
    @Override
    public MainLabTubeStoreDto apply(MainLabTubeStore mainLabTubeStore) {
        return new MainLabTubeStoreDto
                .MainLabTubeStoreDtoBuilder()
                .id(mainLabTubeStore.getId())
                .tubeName(mainLabTubeStore.getItemTubeName())
                .tubeCompany(mainLabTubeStore.getItemTubeCompany())
                .quantity(mainLabTubeStore.getItemTubeQuantity())
                .itemBuyingPrice(mainLabTubeStore.getItemBuyingPrice())
                .barcode(mainLabTubeStore.getItemTubeBarcode())
                .descriptions(mainLabTubeStore.getItemTubeDescription() != null ? mainLabTubeStore.getItemTubeDescription(): null)
                .proDate(mainLabTubeStore.getItemTubeProDate())
                .ExpDate(mainLabTubeStore.getItemTubeExpDate())
                .place(mainLabTubeStore.getItemTubePlaceInStore())
                .storageType(String.valueOf(mainLabTubeStore.getStorageType()))
                .type(String.valueOf(mainLabTubeStore.getTubeType()))
                .image(mainLabTubeStore.getItemImageUrl() !=  null ? mainLabTubeStore.getItemImageUrl() : null)
                .billId(mainLabTubeStore.getBill().getBillId())
                .billId(mainLabTubeStore.getBill().getSupplierDetails())
                .build();
    }
}
