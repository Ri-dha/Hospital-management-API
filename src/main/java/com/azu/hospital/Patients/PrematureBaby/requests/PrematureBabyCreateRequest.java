package com.azu.hospital.Patients.PrematureBaby.requests;

import com.azu.hospital.utils.enums.Gender;

import java.time.LocalTime;

public record PrematureBabyCreateRequest(
        String name,
        String headCircumference,
        Float weight,
        Float height,
        Gender gender,
        String birthDate,
        LocalTime birthTime,
        Long patientId,
        Long wardId,
        Long bedId,
        Long doctorId
) {
}
