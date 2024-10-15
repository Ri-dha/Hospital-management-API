package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.request;

import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;

public record ConsumablesRequestTableRequest(
        Long consumableId,
        Integer quantity,
        OtherItemRequestPlaces itemPlace,
        Long placeId,
        UnitInventoryRequestEnum status

) {
}
