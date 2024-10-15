package com.azu.hospital.Patients.Patient.dto;

import com.azu.hospital.Patients.Patient.request.*;
import com.azu.hospital.utils.enums.patient.BillState;
import com.azu.hospital.utils.enums.patient.PatientTriageEnum;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.image.Image;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class PatientDto {

    private Long id;

    private JobInfo jobInfo;

    private PatientAddress patientAddress;

    private PatientContact patientContact;

    private PatientData patientData;

    private PatientDate patientDate;

    private PatientMedicalInfo patientMedicalInfo;

    private Long doctorId;

    private String doctor;

    private String permanentDoctor;

    private List<Image> images;

    private List<File> files;

    private String image;

    private String wardName;

    private String bedNumber;

    private Instant createdAt;

    private String qrCode;

    private Boolean hasMedicalHistory;

    private PatientTriageEnum triage;

    private BillState billState;

    private String dischargedDate;

    private Long entryNumber;

    private String floorName;

    private List<File> tests;

    private Long createdBy;
    private Long LastModifiedBy;

    @JsonProperty("isArchived")
    private boolean isArchived;

    public PatientDto(
            Long id ,
            JobInfo jobInfo,
            PatientAddress patientAddress,
            PatientContact patientContact,
            PatientData patientData,
            PatientDate patientDate,
            PatientMedicalInfo patientMedicalInfo,
            Long doctorId,
            String doctor,
            String permanentDoctor,
            List<Image> images,
            List<File> files ,
            String image ,
            Instant createdAt,
            String wardName,
            String bedNumber,
            String qrCode,
            Boolean hasMedicalHistory,
            PatientTriageEnum triage,
            BillState billState,
            String dischargedDate,
            Long entryNumber,
            String floorName,
            List<File> tests,
            Long createdBy,
            Long LastModifiedBy,
            boolean isArchived
    ) {
        this.id = id;
        this.jobInfo = jobInfo;
        this.patientAddress = patientAddress;
        this.patientContact = patientContact;
        this.patientData = patientData;
        this.patientDate = patientDate;
        this.patientMedicalInfo = patientMedicalInfo;
        this.doctorId = doctorId;
        this.doctor = doctor;
        this.permanentDoctor = permanentDoctor;
        this.images = images;
        this.files = files;
        this.image = image;
        this.createdAt = createdAt;
        this.wardName = wardName;
        this.bedNumber = bedNumber;
        this.qrCode = qrCode;
        this.hasMedicalHistory = hasMedicalHistory;
        this.triage = triage;
        this.billState = billState;
        this.dischargedDate = dischargedDate;
        this.entryNumber = entryNumber;
        this.floorName = floorName;
        this.tests = tests;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
        this.isArchived = isArchived;
    }


}
