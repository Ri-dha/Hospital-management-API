package com.azu.hospital.bulding.unit.unitDevice.dto;

import com.azu.hospital.bulding.unit.unitDevice.entity.UnitDevice;
import org.springframework.stereotype.Service;

@Service
public class UnitDeviceDtoMapper {
    public UnitDeviceDto toDto(UnitDevice unitDevice){
        return new UnitDeviceDto(
                unitDevice.getId() ,
                unitDevice.getCount() ,
                unitDevice.getDevice().getItemId(),
                unitDevice.getDevice().getItemName() );
    }
}
