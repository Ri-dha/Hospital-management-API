package com.azu.hospital.bulding.ward.medicalDevice.dto;

import lombok.Data;

@Data
public class WardMedicalDeviceDto {

    private Long id;

    private Integer count;

    private Long deviceId;

    private String deviceName;

    public WardMedicalDeviceDto(Long id, Integer count , Long deviceId , String deviceName) {
        this.id = id;
        this.count = count;
        this.deviceId = deviceId;
        this.deviceName = deviceName;

    }
}
