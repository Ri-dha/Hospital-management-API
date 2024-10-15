package com.azu.hospital.consultant.consultantPatient.dto.testDto.mappers;


import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantLabDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantRadiologyDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantUltrasoundDto;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundTestDto;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import org.springframework.stereotype.Service;

@Service
public class ConsultantUltrasoundDtoMapper {
    public ConsultantUltrasoundDto toDto(InternalUltrasoundTest internalUltrasoundTest){
        return new ConsultantUltrasoundDto(
                internalUltrasoundTest.getId(),
                internalUltrasoundTest.getNote(),
                internalUltrasoundTest.getState(),
                internalUltrasoundTest.getConsultantPatient().getId(),
                internalUltrasoundTest.getConsultantPatient().getConsultantPatientInfo().getFullName(),
                internalUltrasoundTest.getCreatedAt()
        );
    }
}
