package com.azu.hospital.bulding.Shift.EmployeeShift.request;

import jakarta.validation.constraints.NotNull;

public record AddUserToShiftRequest(
        @NotNull
        Long shiftId,

        @NotNull
        Long userId,

        @NotNull
        Long wardId


){
}
