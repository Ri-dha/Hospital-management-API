package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dto;

import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;

import java.time.Instant;

public record ConsumablesRequestTableDto(
        Long requestId,
        String consumableName,
        Integer quantity,
        UnitInventoryRequestEnum requestStatus,
        String cause,
        Long createdBy,
        Long LastModifiedBy,
        Instant createAt,
        Instant updateAt
) {


}
