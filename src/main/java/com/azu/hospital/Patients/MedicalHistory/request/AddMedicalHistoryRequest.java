package com.azu.hospital.Patients.MedicalHistory.request;


import com.azu.hospital.utils.enums.patient.PatientTriageEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddMedicalHistoryRequest {

    @NotNull(message = "patient Id Require ")
    private Long patientId;

    @NotNull
    private Long wardId;

    @NotNull
    private Long bedId;

    @NotNull(message = "Dx Complaint Require")
    @NotBlank(message = "Dx Complaint Require")
    private String dx ;

    @NotNull(message = "Contagious Complaint Require")
    @NotBlank(message = "Contagious Complaint Require")
    private String contagious ;

    @NotNull(message = "Chief Complaint Require")
    @NotBlank(message = "Chief Complaint Require")
    private String chiefComplaint ;

    @NotNull(message = "Family History Require")
    @NotBlank(message = "Family History Require")
    private String familyHistory;

    @NotNull(message = "Allergy History Require")
    @NotBlank(message = "Allergy History Require")
    private String drugHistoryAllergy;

    @NotNull(message = "Present History Require")
    @NotBlank(message = "Present History Require")
    private String historyPresentIllness;

    @NotNull(message = "Triage required")
    @Enumerated
    private PatientTriageEnum triage;

    public AddMedicalHistoryRequest() {
    }
}
