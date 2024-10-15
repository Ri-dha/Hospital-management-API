package com.azu.hospital.ph.StockMangment.addOtherItems.dto;

import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class OtherItemFotBillDtoMapper implements Function<OtherItems, OtherItemForBillDto> {
    @Override
    public OtherItemForBillDto apply(OtherItems items) {
        return new OtherItemForBillDto(
                items.getItemId(),
                items.getItemName(),
                items.getItemQuantity(),
                items.getBounce(),
                items.getItemBuyingPrice()
        );
    }
}
