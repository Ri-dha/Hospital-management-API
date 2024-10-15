package com.azu.hospital.bulding.unit.unitDevice.dto;

import lombok.Data;

@Data
public class UnitDeviceDto {

    private Long id;

    private Integer count;

    private Long deviceId;

    private String deviceName;

    public UnitDeviceDto(Long id,Integer count , Long deviceId , String deviceName) {
        this.id = id;
        this.count = count;
        this.deviceId = deviceId;
        this.deviceName = deviceName;

    }
}
