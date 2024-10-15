package com.azu.hospital.bulding.ward.wardDevice.dto;

import lombok.Data;

@Data
public class WardDeviceDto {

    private Long id;

    private Integer count;

    private Long deviceId;

    private String deviceName;

    public WardDeviceDto(Long id,Integer count , Long deviceId , String deviceName) {
        this.id = id;
        this.count = count;
        this.deviceId = deviceId;
        this.deviceName = deviceName;

    }
}
