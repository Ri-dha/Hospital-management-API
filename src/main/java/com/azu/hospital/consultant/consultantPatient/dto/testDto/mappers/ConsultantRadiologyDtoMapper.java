package com.azu.hospital.consultant.consultantPatient.dto.testDto.mappers;

import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantRadiologyDto;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import org.springframework.stereotype.Service;

@Service
public class ConsultantRadiologyDtoMapper {
    public ConsultantRadiologyDto toDto(InternalRadiologyTest internalRadiologyTest){
        return new ConsultantRadiologyDto(
                internalRadiologyTest.getId(),
                internalRadiologyTest.getNote(),
                internalRadiologyTest.getType(),
                internalRadiologyTest.getState(),
                internalRadiologyTest.getConsultantPatient().getId(),
                internalRadiologyTest.getConsultantPatient().getConsultantPatientInfo().getFullName(),
                internalRadiologyTest.getCreatedAt()
        );
    }

}
