package com.azu.hospital.bulding.ward.wardDevice.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class CreateDeviceDataRequest {

    @NotNull
    private Long wardId;

    @NotNull
    @Valid
    private List<DeviceDataRequest> devices;


}
