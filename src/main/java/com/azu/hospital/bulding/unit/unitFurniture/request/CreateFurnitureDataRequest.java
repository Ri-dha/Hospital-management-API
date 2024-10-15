package com.azu.hospital.bulding.unit.unitFurniture.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class CreateFurnitureDataRequest {

    @NotNull
    private Long unitId;

    @NotNull
    @Valid
    private List<FurnitureDataRequest> furnitures;

}
