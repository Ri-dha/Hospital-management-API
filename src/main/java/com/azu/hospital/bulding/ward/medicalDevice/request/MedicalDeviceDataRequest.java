package com.azu.hospital.bulding.ward.medicalDevice.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class MedicalDeviceDataRequest {

    @NotNull
    @NumberFormat
    private Long deviceId;

    @NotNull
    @NumberFormat
    private Integer count;


    private String note;

}
