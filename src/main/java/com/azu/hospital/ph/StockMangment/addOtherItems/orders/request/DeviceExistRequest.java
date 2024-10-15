package com.azu.hospital.ph.StockMangment.addOtherItems.orders.request;

public record DeviceExistRequest(
        Integer deviceCount,
        Boolean isDeviceWorking,
        Boolean isAvailable

) {
}
