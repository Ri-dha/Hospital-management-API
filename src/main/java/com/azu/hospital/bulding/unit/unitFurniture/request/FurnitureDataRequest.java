package com.azu.hospital.bulding.unit.unitFurniture.request;

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


}
