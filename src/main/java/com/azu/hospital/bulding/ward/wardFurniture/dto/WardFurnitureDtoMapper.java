package com.azu.hospital.bulding.ward.wardFurniture.dto;

import com.azu.hospital.bulding.ward.wardFurniture.entity.WardFurniture;
import org.springframework.stereotype.Service;

@Service
public class WardFurnitureDtoMapper {
    public WardFurnitureDto toDto(WardFurniture wardFurniture){
        return new WardFurnitureDto(
                wardFurniture.getId() ,
                wardFurniture.getCount(),
                wardFurniture.getFurniture().getItemId(),
                wardFurniture.getFurniture().getItemName()
        );
    }
}
