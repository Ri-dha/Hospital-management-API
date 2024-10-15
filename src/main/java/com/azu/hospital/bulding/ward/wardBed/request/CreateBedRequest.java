package com.azu.hospital.bulding.ward.wardBed.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBedRequest {

    @NotNull
    private Long wardId;

    @NotNull
    @NotBlank
    private String bedNumber;

    public CreateBedRequest(Long wardId, String bedNumber) {
        this.wardId = wardId;
        this.bedNumber = bedNumber;
    }
}
