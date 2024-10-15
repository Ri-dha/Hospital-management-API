package com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.dto;

import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import lombok.Data;

@Data
public class CancelBottleDto {

    private Long id;

    private BottleTypeEnum bottleType;

    private BloodGroupEnum bloodGroup;

    private String bottleNumber;


    private String donatorName;

    private String donatorPhoneNumber;

    private String fillDate;

    private String expiredDate;

    private BottleStateEnum state;



    public CancelBottleDto(
            Long id,
            BottleTypeEnum bottleType,
            BloodGroupEnum bloodGroup,
            String bottleNumber,
            String donatorName,
            String donatorPhoneNumber,
            String fillDate,
            String expiredDate,
            BottleStateEnum state
    ) {
        this.id = id;
        this.bottleType = bottleType;
        this.bloodGroup = bloodGroup;
        this.bottleNumber = bottleNumber;
        this.donatorName = donatorName;
        this.donatorPhoneNumber = donatorPhoneNumber;
        this.fillDate = fillDate;
        this.expiredDate = expiredDate;
        this.state = state;
    }

    public CancelBottleDto() {
    }
}
