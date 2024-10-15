package com.azu.hospital.consultant.consultantPatient.dto;

import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import org.springframework.stereotype.Service;

@Service
public class ConsultantPatientDtoMapper {
    public ConsultantPatientDto toDto(ConsultantPatient consultantPatient){
        return new ConsultantPatientDto(
                consultantPatient.getId(),
                consultantPatient.getConsultantPatientInfo(),
                consultantPatient.getConsultantPatientAddress(),
                consultantPatient.getConsultantPatientJobInfo(),
                consultantPatient.getNote(),
                consultantPatient.getDoctor().getId(),
                consultantPatient.getDoctor().getUsername(),
                consultantPatient.getFiles(),
                consultantPatient.getImages()
        );
    }
}
