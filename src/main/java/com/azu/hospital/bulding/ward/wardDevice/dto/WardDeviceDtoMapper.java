package com.azu.hospital.bulding.ward.wardDevice.dto;

import com.azu.hospital.bulding.ward.wardDevice.entity.WardDevice;
import org.springframework.stereotype.Service;

@Service
public class WardDeviceDtoMapper {
    public WardDeviceDto toDto(WardDevice wardDevice){
        return new WardDeviceDto(
                wardDevice.getId() ,
                wardDevice.getCount() ,
                wardDevice.getDevice().getItemId(),
                wardDevice.getDevice().getItemName() );
    }
}
