package com.azu.hospital.books.patientBook.request;

public record PatientBookUpdateRequest(
        Long id,
        Long patientId,
        Long doctorId,
        Long nurseId,
        Long wardManagerId,
        Long hospitalManagerId
) {
}
