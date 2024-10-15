package com.azu.hospital.ph.StockMangment.addOtherItems.orders.request;

import com.azu.hospital.utils.enums.DeviceType;

public record UpdateDeviceExistRequest(
        Integer deviceCount,
        Boolean isDeviceWorking,
        Boolean isAvailable,
        DeviceType type,
        Long itemId
) {
}
