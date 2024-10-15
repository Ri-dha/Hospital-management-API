package com.azu.hospital.lab_collection.blood_bank.BottleOrder.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AcceptOrderInfoData(
        @NotNull
        Long bottleOrderInfoId,

        @NotNull
        Integer time,

        @NotNull
        @NotEmpty
        List<Long> bottlesId
) {

}
