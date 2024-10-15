package com.azu.hospital.ecg.internal.dto;

import com.azu.hospital.ecg.internal.entity.Ecg;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EcgDtoMapper implements Function<Ecg, EcgDto> {


    @Override
    public EcgDto apply(Ecg ecg) {
        return new EcgDto(
                ecg.getId(),
                ecg.getNote(),
                ecg.getNoteFromConsultant(),
                ecg.getState(),
                ecg.getFiles(),
                ecg.getPatient().getWard() == null ? null :
                        ecg.getPatient().getWard().getName(),
                ecg.getPatient().getBed() == null ? null :
                        ecg.getPatient().getBed().getBedNumber(),
                ecg.getPatient() == null ? null : ecg.getPatient().getId(),
                ecg.getPatient() == null ? null :
                        ecg.getPatient().getPatientData().getFullName(),
                ecg.getCreatedAt(),
                ecg.getPatient().lastMedicalHistory() == null ? null :
                        ecg.getPatient().lastMedicalHistory().getDx(),
                ecg.getPatient().lastMedicalHistory() == null ? null :
                        ecg.getPatient().lastMedicalHistory().getDrugHistoryAllergy(),
                ecg.getPatient().getPatientData().getGender(),
                ecg.getPatient().getPatientDate().getAge(),
                ecg.getPatient().getPatientData().getWeight(),
                ecg.getPatient().getPatientData().getHeight(),
                ecg.getSrcUser() == null ? null : ecg.getSrcUser().getId(),
                ecg.getSrcUser() == null ? null : ecg.getSrcUser().getUsernameSpecial(),
                ecg.getCreatedBy(),
                ecg.getLastModifiedBy()

        );
    }
}
