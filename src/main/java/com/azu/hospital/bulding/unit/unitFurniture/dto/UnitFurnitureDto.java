package com.azu.hospital.bulding.unit.unitFurniture.dto;

import lombok.Data;

@Data
public class UnitFurnitureDto {

    private Long id;

    private Integer count;

    private Long furnitureId;

    private String furnitureName;

    public UnitFurnitureDto(Long id, Integer count , Long furnitureId , String furnitureName) {
        this.id = id;
        this.count = count;
        this.furnitureId = furnitureId;
        this.furnitureName = furnitureName;

    }
}
