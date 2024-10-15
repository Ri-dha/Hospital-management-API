package com.azu.hospital.bulding.unit.unitFurniture.dto;

import com.azu.hospital.bulding.unit.unitFurniture.entity.UnitFurniture;
import org.springframework.stereotype.Service;

@Service
public class UnitFurnitureDtoMapper {
    public UnitFurnitureDto toDto(UnitFurniture unitFurniture){
        return new UnitFurnitureDto(unitFurniture.getId() , unitFurniture.getCount() ,unitFurniture.getFurniture().getItemId(),
                unitFurniture.getFurniture().getItemName() );
    }
}
