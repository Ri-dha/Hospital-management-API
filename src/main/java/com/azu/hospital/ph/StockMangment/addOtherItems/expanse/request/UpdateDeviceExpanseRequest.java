package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.request;

import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;

public record UpdateDeviceExpanseRequest(
        Integer deviceCount,
        OtherItemRequestPlaces devicePlace,
        Boolean isAvailable,
        Long itemId
) {
}
