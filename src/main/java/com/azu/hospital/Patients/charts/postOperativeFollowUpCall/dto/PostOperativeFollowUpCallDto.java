package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PostOperativeFollowUpCallDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Post Operative Follow Up Call Chart";

    private String dateOfCall;

    private String lsmCaller;

    private Integer numberOfAttempts;

    private String time;

    private String procedure;

    private BigDecimal painLevel;

    private String lsmPhysicianSignature;

    private String anesthesiologistSignature;

    private String dateOfService;

    private List<PatientExperiencingDto> patientExperiencing;
    private Long createdBy;
    private Long LastModifiedBy;

    private String title = "POST OPERATIVE Operative : 8hr FOLLOW UP CALL";
    private String link = "post-operative";

    public PostOperativeFollowUpCallDto() {
    }

    public PostOperativeFollowUpCallDto(
            Long id,
            String chartName,
            String dateOfCall,
            String lsmCaller,
            Integer numberOfAttempts,
            String time,
            String procedure,
            BigDecimal painLevel,
            String lsmPhysicianSignature,
            String anesthesiologistSignature,
            String dateOfService,
            List<PatientExperiencingDto> patientExperiencing,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.dateOfCall = dateOfCall;
        this.lsmCaller = lsmCaller;
        this.numberOfAttempts = numberOfAttempts;
        this.time = time;
        this.procedure = procedure;
        this.painLevel = painLevel;
        this.lsmPhysicianSignature = lsmPhysicianSignature;
        this.anesthesiologistSignature = anesthesiologistSignature;
        this.dateOfService = dateOfService;
        this.patientExperiencing = patientExperiencing;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
