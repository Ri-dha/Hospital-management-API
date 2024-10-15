package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateConsultantPatientMedicalHistoryRequest {

    @NotNull
    private Long medicalHistoryId;

    private String dx;

    private String historyOfPresentIllness;

    private String pastMedicalHistory;

    private String pastSurgicalHistory;

    private String allergy;

    private String contagiousDisease;

    public UpdateConsultantPatientMedicalHistoryRequest() {
    }
}
