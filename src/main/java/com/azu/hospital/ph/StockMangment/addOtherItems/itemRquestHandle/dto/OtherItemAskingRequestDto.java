package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dto;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;

import java.time.Instant;
import java.util.List;

public record OtherItemAskingRequestDto(
        Long requestId,
        Long itemId,
        String itemName,
        Integer quantity,
        UnitInventoryRequestEnum requestStatus,
        Long signatures,
        String cause,
        String note,
        Instant createAt,
        Instant updateAt
) {



}
