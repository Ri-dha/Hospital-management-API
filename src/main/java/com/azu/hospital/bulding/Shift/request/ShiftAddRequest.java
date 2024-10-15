package com.azu.hospital.bulding.Shift.request;


import com.azu.hospital.utils.enums.Shift.ShiftType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ShiftAddRequest(

        @NotNull
        String name,

        @NotNull
        @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]" , message = "fromTime must be in format HH:mm")
        String fromTime,

        @NotNull
        @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]" , message = "toTime must be in format HH:mm")
        String toTime,

        @NotNull
        @Enumerated(EnumType.STRING)
        ShiftType type

) {
}
