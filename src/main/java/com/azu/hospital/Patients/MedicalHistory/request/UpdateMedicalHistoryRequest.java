package com.azu.hospital.Patients.MedicalHistory.request;


import com.azu.hospital.utils.enums.patient.PatientTriageEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateMedicalHistoryRequest {


    private Long wardId;

    private Long bedId;

    private String dx ;

    private String contagious;

    private String chiefComplaint ;

    private String familyHistory;

    private String drugHistoryAllergy;

    private String historyPresentIllness;

    private PatientTriageEnum triage;

    public UpdateMedicalHistoryRequest() {
    }
}
