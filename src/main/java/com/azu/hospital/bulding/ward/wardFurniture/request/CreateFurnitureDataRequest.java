package com.azu.hospital.bulding.ward.wardFurniture.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class CreateFurnitureDataRequest {

    @NotNull
    private Long wardId;

    @NotNull
    @Valid
    private List<FurnitureDataRequest> furnitures;

    public CreateFurnitureDataRequest() {
    }


}
