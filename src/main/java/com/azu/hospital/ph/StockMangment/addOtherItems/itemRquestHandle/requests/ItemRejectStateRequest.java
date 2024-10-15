package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record ItemRejectStateRequest(
//        @NotEmpty(message = "Status requires")
//        @NotNull(message = "Status requires")
//        @NotBlank(message = "Status requires")
        String status,

        String cause
) {
}
