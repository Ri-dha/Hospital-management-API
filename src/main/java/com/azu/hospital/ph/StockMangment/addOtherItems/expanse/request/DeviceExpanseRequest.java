package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.request;


public record DeviceExpanseRequest(
        Integer deviceCount,
        String devicePlace,
        Boolean isAvailable

) {
}
