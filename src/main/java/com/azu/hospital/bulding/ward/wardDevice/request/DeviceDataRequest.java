package com.azu.hospital.bulding.ward.wardDevice.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class DeviceDataRequest {

    @NotNull
    @NumberFormat
    private Long deviceId;

    @NotNull
    @NumberFormat
    private Integer count;


    private String note;

}
