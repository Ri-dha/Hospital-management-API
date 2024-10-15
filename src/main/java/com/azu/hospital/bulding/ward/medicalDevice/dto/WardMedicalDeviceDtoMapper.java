package com.azu.hospital.bulding.ward.medicalDevice.dto;

import com.azu.hospital.bulding.ward.medicalDevice.entity.WardMedicalDevice;
import org.springframework.stereotype.Service;

@Service
public class WardMedicalDeviceDtoMapper {
    public WardMedicalDeviceDto toDto(WardMedicalDevice wardMedicalDevice){
        return new WardMedicalDeviceDto(
                wardMedicalDevice.getId() ,
                wardMedicalDevice.getCount() ,
                wardMedicalDevice.getDevice().getItemId(),
                wardMedicalDevice.getDevice().getItemName() );
    }
}
