package com.azu.hospital.Patients.MedicalHistory.dto;

import com.azu.hospital.Patients.MedicalHistory.entity.MedicalHistory;
import com.azu.hospital.Patients.MedicalHistory.request.UpdateMedicalHistoryRequest;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryDtoMapper {

    public MedicalHistoryDto toDto(MedicalHistory medicalHistory){
        return  new MedicalHistoryDto(
                medicalHistory.getId(),
                medicalHistory.getDx(),
                medicalHistory.getContagious(),
                medicalHistory.getChiefComplaint(),
                medicalHistory.getFamilyHistory(),
                medicalHistory.getDrugHistoryAllergy(),
                medicalHistory.getHistoryPresentIllness(),
                medicalHistory.getDaysOffWritten() ,
                medicalHistory.getPatient().getWard() == null ? null : medicalHistory.getPatient().getWard().getWardId() ,
                medicalHistory.getPatient().getBed() == null ? null : medicalHistory.getPatient().getBed().getId() ,
                medicalHistory.getPatient().getWard() == null ? null :
                        medicalHistory.getPatient().getWard().getFloor().getFloorId(),
                medicalHistory.getPatient().getWard() == null ? null : medicalHistory.getPatient().getWard().getName() ,
                medicalHistory.getPatient().getBed() == null ? null : medicalHistory.getPatient().getBed().getBedNumber() ,
                medicalHistory.getPatient().getWard() == null ? null : medicalHistory.getPatient().getWard().getFloor().getFloorName(),
                medicalHistory.getTriage(),
                medicalHistory.getDaysOffWritten()
        );
    }

    public MedicalHistory toEntity(UpdateMedicalHistoryRequest request){
        return  new MedicalHistory(
                request.getDx(),
                request.getContagious(),
                request.getChiefComplaint(),
                request.getFamilyHistory(),
                request.getDrugHistoryAllergy(),
                request.getHistoryPresentIllness(),
                request.getTriage() == null ? null : request.getTriage()
        );
    }

}
