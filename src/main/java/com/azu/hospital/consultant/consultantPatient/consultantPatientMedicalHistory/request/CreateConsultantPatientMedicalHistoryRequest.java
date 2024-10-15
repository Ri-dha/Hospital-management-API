package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateConsultantPatientMedicalHistoryRequest {

    @NotNull
    private Long patientId;

    @NotNull
    @NotBlank
    private String dx;


    @NotNull
    @NotBlank
    private String historyOfPresentIllness;


    @NotNull
    @NotBlank
        private String pastMedicalHistory;


    @NotNull
    @NotBlank
    private String pastSurgicalHistory;

    @NotNull
    @NotBlank
    private String allergy;

    @NotNull
    @NotBlank
    private String contagiousDisease;

    public CreateConsultantPatientMedicalHistoryRequest() {
    }
}
