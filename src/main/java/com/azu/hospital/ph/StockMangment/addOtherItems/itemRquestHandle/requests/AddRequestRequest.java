package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests;

import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record AddRequestRequest(

        @NotNull
        @NumberFormat
        Integer quantity,
        @NotNull
        OtherItemRequestPlaces itemPlace,
        String note,
        @NotNull
        Long placeId,
        @NotNull
        Long userId
) {
}
