package com.azu.hospital.bulding.floor.dto;


import com.azu.hospital.bulding.floor.entity.Floor;
import com.azu.hospital.bulding.ward.dto.WardDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FloorWithWardsDtoMapper implements Function<Floor,FloorWithWardsDto>{

    private final WardDtoMapper wardDtoMapper;

    @Autowired
    public FloorWithWardsDtoMapper(WardDtoMapper wardDtoMapper) {
        this.wardDtoMapper = wardDtoMapper;
    }

    @Override
    public FloorWithWardsDto apply(Floor floor) {
        return new FloorWithWardsDto(
                floor.getFloorId(),
                floor.getFloorNumber(),
                floor.getFloorName(),
                floor.getCreatedAt(),
                floor.getWards().stream()
                        .map(wardDtoMapper)
                        .toList()
        );
    }
}
