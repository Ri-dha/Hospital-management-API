package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests;


import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;

public record OtherItemAskingRegistrationRequest(
        Long ItemId,
        Long userId,
        Integer quantity,
        OtherItemRequestPlaces itemPlace,
        Long placeId
) {
}
