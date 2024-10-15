package com.azu.hospital.lab_collection.blood_bank.BottleOrder.dto;


import com.azu.hospital.lab_collection.blood_bank.Bottle.dto.BottleDtoMapper;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity.BottleOrder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
public class BottleOrderDtoMapper {

    private final BottleDtoMapper bottleDtoMapper;

    public BottleOrderDtoMapper(BottleDtoMapper bottleDtoMapper) {
        this.bottleDtoMapper = bottleDtoMapper;
    }


    public BottleOrderDto toDto(BottleOrder bottleOrder) {


        return new BottleOrderDto(
                bottleOrder.getId(),
                bottleOrder.getIsReserved(),
                bottleOrder.getNote(),
                bottleOrder.getState(),
                bottleOrder.getPatient().getId(),
                bottleOrder.getPatient().getPatientData().getFullName(),
                bottleOrder.getBottleType(),
                bottleOrder.getBloodGroup(),
                bottleOrder.getCount(),
                bottleOrder.getTime(),
                bottleOrder.getReceivedNote(),
                bottleOrder.getBottles()
                        .stream()
                        .map(bottleDtoMapper::toDto)
                        .toList()
        );
    }
}
