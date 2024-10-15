package com.azu.hospital.bulding.unit.request;

import lombok.Data;

@Data
public class UpdateUnitRequest {

    private String name;

    private Long departmentId;

    private Long floorId;

    private Long managerId;

    private Long assistanceId;

}
