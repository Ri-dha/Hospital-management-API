package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.request;

import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;

public record ConsumablesRequestTableUpdateRequest(
        Long consumableId,
        Integer quantity,
        OtherItemRequestPlaces itemPlace,
        Long placeId

) {
}
