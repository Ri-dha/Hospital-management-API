
package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dto;

import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import org.springframework.stereotype.Service;

@Service
public class DeviceExpanseDtoMapper  {



    public DeviceExpanseDto mapToDto(DeviceExpanseTable deviceExpanseTable) {
        return DeviceExpanseDto.builder()
                .deviceId(deviceExpanseTable.getDeviceId())
                .deviceName(deviceExpanseTable.getDeviceExistsTable().getDeviceDetails().getItemName())
                .deviceType(deviceExpanseTable.getDeviceExistsTable().getDeviceDetails().getDeviceType().getName())
                .deviceCount(deviceExpanseTable.getQuantity())
                .deviceInMainTableStoreId(deviceExpanseTable.getDeviceExistsTable().getDeviceId())
                .existDeviceId(deviceExpanseTable.getDeviceExistsTable().getDeviceId())
                .deviceSerialNo(deviceExpanseTable.getDeviceExistsTable().getDeviceDetails().getSerialNumber())
                .deviceBarcode(deviceExpanseTable.getDeviceExistsTable().getDeviceDetails().getItemBarcode())
                .build();
    }
}