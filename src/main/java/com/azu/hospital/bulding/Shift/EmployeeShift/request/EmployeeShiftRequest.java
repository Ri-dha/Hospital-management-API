package com.azu.hospital.bulding.Shift.EmployeeShift.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EmployeeShiftRequest(

        @NotNull
        Long shiftId,

        @NotNull
        List<Long> usersIds,

        @NotNull
        Long wardId,



        @NotNull
        Integer startDay,

        @NotNull
        Integer endDay

) {
}
