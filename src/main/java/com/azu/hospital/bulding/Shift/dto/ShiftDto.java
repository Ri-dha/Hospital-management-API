package com.azu.hospital.bulding.Shift.dto;


import java.time.Instant;

public record ShiftDto(

        Long id,
        String name,

        String fromTime,

        String toTime,


        Instant createdAt,

        Instant updatedAt

) {
}
