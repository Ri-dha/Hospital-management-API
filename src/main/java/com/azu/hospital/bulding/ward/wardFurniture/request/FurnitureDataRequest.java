package com.azu.hospital.bulding.ward.wardFurniture.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class FurnitureDataRequest {

    @NotNull
    @NumberFormat
    private Long furnitureId;

    @NotNull
    @NumberFormat
    private Integer count;

    private String note;


    public FurnitureDataRequest() {
    }
}
