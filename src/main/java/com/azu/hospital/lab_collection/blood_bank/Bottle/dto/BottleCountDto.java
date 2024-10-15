package com.azu.hospital.lab_collection.blood_bank.Bottle.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BottleCountDto {

    private String bottleType;
    private String bloodGroup;
    private Long count;

    public BottleCountDto() {
    }

    public BottleCountDto(String bottleType, Long count) {
        this.bottleType = bottleType;
        this.count = count;
    }

    public BottleCountDto(String bottleType, String bloodGroup, Long count) {
        this.bottleType = bottleType;
        this.bloodGroup = bloodGroup;
        this.count = count;
    }

}
