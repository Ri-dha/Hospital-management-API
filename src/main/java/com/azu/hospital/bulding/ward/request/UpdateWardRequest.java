package com.azu.hospital.bulding.ward.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateWardRequest {

    private String name;

    private Long departmentId;

    private Long floorId;

    private Long managerId;

    private Long assistanceId;

}
