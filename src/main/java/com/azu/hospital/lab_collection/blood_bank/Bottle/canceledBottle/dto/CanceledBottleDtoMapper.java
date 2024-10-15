package com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.dto;

import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import org.springframework.stereotype.Service;

@Service
public class CanceledBottleDtoMapper {

    public CancelBottleDto toDto(Bottle bottle){
        return new CancelBottleDto(
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
