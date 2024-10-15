package com.azu.hospital.books.patientBook.request;

import com.azu.hospital.books.patientBook.PatientBookType;

public record PatientBookCreateRequest(

        PatientBookType patientBookType,
        Long patientId,
        Long doctorId,
        Long nurseId,
        Long wardManagerId,
        Long hospitalManagerId
) {
}
