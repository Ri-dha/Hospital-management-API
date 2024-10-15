package com.azu.hospital.bulding.unit.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUnitRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Long departmentId;

    @NotNull
    private Long floorId;

    @NotNull
    private Long managerId;

    @NotNull
    private Long assistanceId;


}
