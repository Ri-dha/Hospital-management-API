package com.azu.hospital.ph.StockMangment.addOtherItems.orders.dto;

import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DeviceExistDtoMapper implements Function<DeviceExistsTable, DeviceExistDto> {


    @Override
    public DeviceExistDto apply(DeviceExistsTable deviceExistsTable) {
        return new DeviceExistDto
                .DeviceExistDtoBuilder()
                .itemId(deviceExistsTable.getDeviceId())
                .itemName(deviceExistsTable.getDeviceDetails().getItemName())
                .deviceType(deviceExistsTable.getDeviceDetails().getDeviceType().getName())
                .itemQuantity(deviceExistsTable.getQuantity())
                .deviceInMainTableStoreId(deviceExistsTable.getDeviceDetails().getItemId())
                .serialNumber(deviceExistsTable.getDeviceDetails().getSerialNumber())
                .itemBarcode(deviceExistsTable.getDeviceDetails().getItemBarcode())
                .isDeviceWorking(deviceExistsTable.getIsDeviceWorking())
                .isAvailable(deviceExistsTable.getIsAvailable())
                .itemCompany(deviceExistsTable.getDeviceDetails().getItemCompany())
                .itemBuyingPrice(deviceExistsTable.getDeviceDetails().getItemBuyingPrice())
                .itemDescription(deviceExistsTable.getDeviceDetails().getItemDescription())
                .itemProDate(deviceExistsTable.getDeviceDetails().getItemProDate())
                .itemImageUrl(deviceExistsTable.getDeviceDetails().getItemImageUrl())
                .files(deviceExistsTable.getDeviceDetails().getFilesUrls())
                .billId(deviceExistsTable.getDeviceDetails().getBill().getBillId())
                .supplierName(deviceExistsTable.getDeviceDetails().getBill().getSupplier())
                .inventoryPlace(deviceExistsTable.getExpanseTable().getDevicePlace().name())
                .updateAt(deviceExistsTable.getDeviceDetails().getUpdateAt())
                .bonus(deviceExistsTable.getDeviceDetails().getBounce())
                .deviceWarrantyDate(deviceExistsTable.getDeviceDetails().getDeviceWarrantyDate())
                .build();
    }
}
