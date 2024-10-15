package com.azu.hospital.bulding.ward.medicalDevice.dto;

import lombok.Data;

@Data
public class DeviceSumDTO {
    private String itemName;
    private Long sumCount;


    public DeviceSumDTO(String itemName, Long sumCount) {
        this.itemName = itemName;
        this.sumCount = sumCount;
    }
}