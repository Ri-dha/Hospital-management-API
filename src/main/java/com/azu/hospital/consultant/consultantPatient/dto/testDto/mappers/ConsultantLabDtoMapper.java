package com.azu.hospital.consultant.consultantPatient.dto.testDto.mappers;

import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantLabDto;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import org.springframework.stereotype.Service;


@Service
public class ConsultantLabDtoMapper {

    public ConsultantLabDto toDto(InternalLabTest internalLabTest){
       return new ConsultantLabDto(
               internalLabTest.getId(),
               internalLabTest.getCreatedAt(),
               internalLabTest.getState().getName(),
               internalLabTest.getNote(),
               internalLabTest.getUploader().getId(),
               internalLabTest.getUploader().getUsername(),
               internalLabTest.getConsultantPatient().getId(),
               internalLabTest.getConsultantPatient().getConsultantPatientInfo().getFullName()
       );
    }
}
