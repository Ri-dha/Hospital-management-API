package com.azu.hospital.Patients.surgicalList.dto;

import com.azu.hospital.Patients.surgicalList.entity.SurgicalList;
import org.springframework.stereotype.Service;

@Service
public class SurgicalListDtoMapper {

    public SurgicalListDto toDto(SurgicalList surgicalList){
        return new SurgicalListDto(
                surgicalList.getId(),
                surgicalList.getSurgicalName(),
                surgicalList.getAnesthesiaType(),
                surgicalList.getState(),
                surgicalList.getPatient().getId(),
                surgicalList.getPatient().getPatientData().getFullName(),
                surgicalList.getPatient().getPatientDate().getAge(),
                surgicalList.getSurgicalDate(),
                surgicalList.getCreatedAt(),
                surgicalList.getUpdatedAt() == null ? null : surgicalList.getUpdatedAt()
        );
    };

}
