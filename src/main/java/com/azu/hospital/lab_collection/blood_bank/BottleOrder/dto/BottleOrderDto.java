package com.azu.hospital.lab_collection.blood_bank.BottleOrder.dto;

import com.azu.hospital.lab_collection.blood_bank.Bottle.dto.BottleDto;
import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BottleOrderDto {

    private Long id;
    private Boolean isReserved;

    private String note;

    private Long patientId;

    private String patientName;

    private BottleTypeEnum bottleType;

    private BloodGroupEnum bloodGroup;

    private Integer count;

    private Integer time;

    private String receivedNote;

    private List<BottleDto> bottles;

    private BottleStateEnum state;


    public BottleOrderDto() {
    }

    public BottleOrderDto(
            Long id,
            Boolean isReserved,
            String note,
            BottleStateEnum state,
            Long patientId,
            String patientName,
            BottleTypeEnum bottleType,
            BloodGroupEnum bloodGroup,
            Integer count,
            Integer time,
            String receivedNote ,
            List<BottleDto> bottles
    ) {
        this.id = id;
        this.isReserved = isReserved;
        this.note = note;
        this.state = state;
        this.patientId = patientId;
        this.patientName = patientName;
        this.bottleType = bottleType;
        this.bloodGroup = bloodGroup;
        this.count = count;
        this.time = time;
        this.receivedNote = receivedNote;
        this.bottles = bottles;
    }


}
