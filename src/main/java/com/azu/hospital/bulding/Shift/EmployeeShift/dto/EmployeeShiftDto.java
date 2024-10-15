package com.azu.hospital.bulding.Shift.EmployeeShift.dto;


import java.time.Instant;

public record EmployeeShiftDto(

         Long id,
         Long nurseId,
         String nurseName,
         Long shiftId,
         String shiftName,

         Long wardId,
         String wardName,

         Instant createdAt,

         Instant updatedAt

) {
}
