package com.azu.hospital.bulding.unit.unitDevice.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class CreateDeviceDataRequest {

    @NotNull
    private Long unitId;

    @NotNull
    @Valid
    private List<DeviceDataRequest> devices;


}
