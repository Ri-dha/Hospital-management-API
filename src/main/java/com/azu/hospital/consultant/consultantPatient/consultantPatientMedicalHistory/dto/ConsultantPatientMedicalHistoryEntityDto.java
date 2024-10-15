package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dto;

import lombok.Data;

@Data
public class ConsultantPatientMedicalHistoryEntityDto {

    private String dx;

    private String historyOfPresentIllness;

    private String pastMedicalHistory;

    private String pastSurgicalHistory;

    private String allergy ;

    private String contagiousDisease;
    public ConsultantPatientMedicalHistoryEntityDto(
            String dx,
            String historyOfPresentIllness,
            String pastMedicalHistory,
            String pastSurgicalHistory,
            String allergy ,
            String contagiousDisease
    ) {
        this.dx = dx;
        this.historyOfPresentIllness = historyOfPresentIllness;
        this.pastMedicalHistory = pastMedicalHistory;
        this.pastSurgicalHistory = pastSurgicalHistory;
        this.allergy = allergy;
        this.contagiousDisease = contagiousDisease;
    }


    public ConsultantPatientMedicalHistoryEntityDto() {
    }
}
