package com.azu.hospital.ph.main_data_store.drugs_item.dto;

import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class DrugItemDtoMapper implements Function<DrugsItem, DrugItemDto> {
    @Override
    public DrugItemDto apply(DrugsItem drugsItem) {
        return new DrugItemDto
                .DrugItemDtoBuilder()
                .drugId(drugsItem.getId())
                .drugTradeName(drugsItem.getItemName())
                .drugScientificName(drugsItem.getDrugScientificName())
                .drugCompany(drugsItem.getItemCompany())
                .dose(drugsItem.getDose())
                .type(drugsItem.getType())
                .quantity(drugsItem.getQuantity())
                .drugBonus(drugsItem.getDrugBonus())
                .description(drugsItem.getDescription())
                .expDate(drugsItem.getExpDate())
                .proDate(drugsItem.getProDate())
                .barcode(drugsItem.getBarcode())
                .ndcNumber(drugsItem.getNdcNumber())
                .price(drugsItem.getPrice())
                .drugSellingPrice(drugsItem.getDrugSellingPrice())
                .typOfCurrency(drugsItem.getTypeOfCurrency())
                .exchange(drugsItem.getExchange())
                .drugImageUrls(drugsItem.getImageUrls())
                .isBack(drugsItem.getIsBack())
                .isDrugExists(drugsItem.getIsDrugExist())
                .billId(1L)
                .supplier("None")
                .build();
    }
}
