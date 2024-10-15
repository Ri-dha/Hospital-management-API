package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dto;

import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.entity.ConsultantPatientMedicalHistory;
import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.request.UpdateConsultantPatientMedicalHistoryRequest;
import com.azu.hospital.consultant.consultantPatient.dao.ConsultantPatientDao;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import org.springframework.stereotype.Service;

@Service
public class ConsultantPatientMedicalHistoryDtoMapper {

    public ConsultantPatientMedicalHistoryDto toDto(ConsultantPatientMedicalHistory medicalHistory){
        return new ConsultantPatientMedicalHistoryDto(
                medicalHistory.getId(),
                medicalHistory.getConsultantPatient().getId(),
                medicalHistory.getPastMedicalHistory(),
                medicalHistory.getHistoryOfPresentIllness(),
                medicalHistory.getPastMedicalHistory(),
                medicalHistory.getPastSurgicalHistory(),
                medicalHistory.getCreatedAt(),
                medicalHistory.getUpdatedAt()
        );
    }

    public ConsultantPatientMedicalHistoryEntityDto ToEntity(UpdateConsultantPatientMedicalHistoryRequest medicalHistory){
        return new ConsultantPatientMedicalHistoryEntityDto(
                medicalHistory.getDx(),
                medicalHistory.getHistoryOfPresentIllness(),
                medicalHistory.getPastMedicalHistory(),
                medicalHistory.getPastSurgicalHistory(),
                medicalHistory.getAllergy(),
                medicalHistory.getContagiousDisease()
        );
    }
}
