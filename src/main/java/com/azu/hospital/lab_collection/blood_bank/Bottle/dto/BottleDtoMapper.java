package com.azu.hospital.lab_collection.blood_bank.Bottle.dto;

import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class BottleDtoMapper {

    public BottleDto toDto(Bottle bottle){
        return new BottleDto(
                bottle.getId(),
                bottle.getBottleType(),
                bottle.getBloodGroup(),
                bottle.getBottleNumber(),
                bottle.getDonatorName(),
                bottle.getDonatorPhoneNumber(),
                bottle.getFillDate(),
                bottle.getExpiredDate(),
                bottle.getState()

        );
    }


}
