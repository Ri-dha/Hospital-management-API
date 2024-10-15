package com.azu.hospital.bulding.ward.medicalDevice.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class CreateMedicalDeviceDataRequest {

    @NotNull
    private Long wardId;

    @NotNull
    @Valid
    private List<MedicalDeviceDataRequest> devices;


}
