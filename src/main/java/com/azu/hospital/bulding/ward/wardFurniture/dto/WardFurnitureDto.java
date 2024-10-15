package com.azu.hospital.bulding.ward.wardFurniture.dto;

import lombok.Data;

@Data
public class WardFurnitureDto {

    private Long id;

    private Integer count;

    private Long furnitureId;

    private String furnitureName;

    public WardFurnitureDto(Long id, Integer count , Long furnitureId , String furnitureName) {
        this.id = id;
        this.count = count;
        this.furnitureId = furnitureId;
        this.furnitureName = furnitureName;

    }
}
