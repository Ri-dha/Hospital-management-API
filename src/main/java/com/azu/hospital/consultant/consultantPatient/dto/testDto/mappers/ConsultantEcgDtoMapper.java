package com.azu.hospital.consultant.consultantPatient.dto.testDto.mappers;

import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantEcgDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantLabDto;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import org.springframework.stereotype.Service;

@Service
public class ConsultantEcgDtoMapper {
    public ConsultantEcgDto toDto(Ecg ecg){
        return new ConsultantEcgDto(
                ecg.getId(),
                ecg.getConsultantPatient().getId(),
                ecg.getConsultantPatient().getConsultantPatientInfo().getFullName(),
                ecg.getSrcUser() == null ? null : ecg.getSrcUser().getId(),
                ecg.getSrcUser() == null ? null : ecg.getSrcUser().getUsernameSpecial(),
                ecg.getConsultantPatient().getDoctor().getId(),
                ecg.getConsultantPatient().getDoctor().getUsernameSpecial(),
                ecg.getState(),
                ecg.getFiles(),
                ecg.getCreatedAt(),
                ecg.getNote()
        );
    }
}
