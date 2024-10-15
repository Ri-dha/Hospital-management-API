package com.azu.hospital.Patients.MedicalHistory.dto;

import com.azu.hospital.utils.enums.patient.PatientTriageEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MedicalHistoryDto {

    private Long id;


    private String dx;

    private String contagious;

    private String chiefComplaint ;

    private String familyHistory;

    private String drugHistoryAllergy;

    private String historyPresentIllness;

    private Instant daysOffWritten;

    private Long wardId;

    private Long bedId;

    private Long floorId;

    private String ward;

    private String bedNumber;

    private String floorName;

    private Instant createdAt;

    private PatientTriageEnum triage;


    public MedicalHistoryDto() {
    }

    public MedicalHistoryDto(
            Long id, String dx,
            String contagious,
            String chiefComplaint,
            String familyHistory,
            String drugHistoryAllergy,
            String historyPresentIllness,
            Instant daysOffWritten ,
            Long wardId ,
            Long bedId ,
            Long floorId ,
            String ward ,
            String bedNumber ,
            String floorName ,
            PatientTriageEnum triage ,
            Instant createdAt
    ) {
        this.id = id;
        this.dx = dx;
        this.contagious = contagious;
        this.chiefComplaint = chiefComplaint;
        this.familyHistory = familyHistory;
        this.drugHistoryAllergy = drugHistoryAllergy;
        this.historyPresentIllness = historyPresentIllness;
        this.daysOffWritten = daysOffWritten;
        this.wardId = wardId ;
        this.bedId  = bedId;
        this.floorId = floorId;
        this.ward = ward ;
        this.bedNumber  = bedNumber;
        this.floorName = floorName;
        this.triage = triage;
        this.createdAt = createdAt;
    }
}
