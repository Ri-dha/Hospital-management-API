package com.azu.hospital.ph.StockMangment.addOtherItems.dto;


import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OtherItemDtoMapper implements Function<OtherItems, OtherItemDto> {

    @Override
    public OtherItemDto apply(OtherItems otherItems) {
        if (otherItems == null) {
            return null;
        }

        return OtherItemDto.builder()
                .itemId(otherItems.getItemId())
                .itemName(otherItems.getItemName())
                .itemCompany(otherItems.getItemCompany())
                .itemQuantity(otherItems.getItemQuantity())
                .itemBuyingPrice(otherItems.getItemBuyingPrice())
                .itemBarcode(otherItems.getItemBarcode())
                .itemDescription(otherItems.getItemDescription())
                .serialNumber(otherItems.getSerialNumber())
                .itemProDate(otherItems.getItemProDate())
                .deviceWarrantyDate(otherItems.getDeviceWarrantyDate())
                .deviceType(otherItems.getDeviceType())
                .bounce(otherItems.getBounce())
                .itemImageUrl(otherItems.getItemImageUrl())
                .files(otherItems.getFilesUrls())
                .billId(otherItems.getBill().getBillId())
                .supplierName(otherItems.getBill().getSupplierDetails())
                .inventoryPlace(otherItems.getDeviceExistsTable() == null ? null : otherItems.getDeviceExistsTable().getExpanseTable().getDevicePlace().name())
                .updateAt(otherItems.getUpdateAt())
                .build();
    }
}

