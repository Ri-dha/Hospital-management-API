package com.azu.hospital.books.patientBook.dto;


import com.azu.hospital.books.patientBook.entity.PatientBook;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PatientBookDtoMapper implements Function<PatientBook,PatientBookDto> {
    @Override
    public PatientBookDto apply(PatientBook patientBook) {
        return new PatientBookDto(
                patientBook.getId(),
                patientBook.getPatientBookType(),
                patientBook.getPatient().getId(),
                patientBook.getPatient().getPatientData().getFullName(),
                patientBook.getNurse().getId(),
                patientBook.getNurse().getUsernameSpecial(),
                patientBook.getWardManger().getId(),
                patientBook.getWardManger().getUsernameSpecial(),
                patientBook.getHospitalManager().getId(),
                patientBook.getHospitalManager().getUsernameSpecial(),
                patientBook.getDoctor().getId(),
                patientBook.getDoctor().getUsernameSpecial()
        );
    }
}
