package com.azu.hospital.bulding.floor.dto;

import com.azu.hospital.bulding.floor.entity.Floor;
import org.springframework.stereotype.Service;

@Service
public class FloorDtoMapper {

    public FloorDto toDto(Floor floor){
        return new FloorDto(
                floor.getFloorId(),
                floor.getFloorNumber(),
                floor.getFloorName(),
                floor.getCreatedAt() == null ? null : floor.getCreatedAt()
        );
    }

    public Floor toEntity(FloorDto dto){
        Floor floor = new Floor();
        floor.setFloorNumber(dto.getFloorNumber());
        floor.setFloorName(dto.getFloorName());
        return floor;
    }

}
