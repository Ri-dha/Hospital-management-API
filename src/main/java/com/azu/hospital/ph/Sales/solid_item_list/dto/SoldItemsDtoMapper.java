package com.azu.hospital.ph.Sales.solid_item_list.dto;

import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SoldItemsDtoMapper implements Function<SoldItems, SoldItemsDto> {
    @Override
    public SoldItemsDto apply(SoldItems soldItems) {
        return new SoldItemsDto(
                soldItems.getId(),
                soldItems.getItemName(),
                soldItems.getQuantity()
        );
    }
}
