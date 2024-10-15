package com.azu.hospital.Patients.charts.postDischargeInstructions.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PostDischargeInstructionsDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Post Discharge Instructions Chart";

    private String anticoagulantAdvisement;

    private String title = "POST OPERATIVE DISCHARGE INSTRUCTIONS";
    private String link = "instructions";

    private String phoneNumber;

    private String hospitalName;

    private String patientSignature;

    private String patientSignatureDate;

    private String patientSignatureTime;

    private String registeredNurseName;

    private String registeredDate;

    private String registeredTime;

    private Long createdBy;
    private Long LastModifiedBy;


    public PostDischargeInstructionsDto() {
    }


    public PostDischargeInstructionsDto(
            Long id,
            String chartName,
            String anticoagulantAdvisement,
            String doctorName,
            String phoneNumber,
            String hospitalName,
            String patientSignature,
            String patientSignatureDate,
            String patientSignatureTime,
            String registeredNurseName,
            String registeredDate,
            String registeredTime,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.anticoagulantAdvisement = anticoagulantAdvisement;
        this.doctorName = doctorName;
        this.phoneNumber = phoneNumber;
        this.hospitalName = hospitalName;
        this.patientSignature = patientSignature;
        this.patientSignatureDate = patientSignatureDate;
        this.patientSignatureTime = patientSignatureTime;
        this.registeredNurseName = registeredNurseName;
        this.registeredDate = registeredDate;
        this.registeredTime = registeredTime;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
