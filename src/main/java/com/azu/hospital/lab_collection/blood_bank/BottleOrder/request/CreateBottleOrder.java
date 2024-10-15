package com.azu.hospital.lab_collection.blood_bank.BottleOrder.request;

import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateBottleOrder(
        @NotNull
        Long patientId,
        String note,
        Boolean isReserved,
        @NotNull
        BottleTypeEnum bottleType,
        BloodGroupEnum bloodGroup,

        @Min(value = 1, message = "Count must be greater than 0")
        Integer count

) {}