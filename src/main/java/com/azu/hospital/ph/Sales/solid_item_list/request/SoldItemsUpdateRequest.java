package com.azu.hospital.ph.Sales.solid_item_list.request;

public record SoldItemsUpdateRequest(

        String drugTradeName,
        Integer quantity
) {
}
