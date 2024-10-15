package com.azu.hospital.bulding.ward.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CreateWardRequest {

    private String name;


    private Long departmentId;


    private Long floorId;


    private Long managerId;


    private Long assistanceId;

    public CreateWardRequest(String name, Long departmentId, Long floorId, Long managerId, Long assistanceId) {
        this.name = name;
        this.departmentId = departmentId;
        this.floorId = floorId;
        this.managerId = managerId;
        this.assistanceId = assistanceId;
    }

    public CreateWardRequest() {
    }
}
