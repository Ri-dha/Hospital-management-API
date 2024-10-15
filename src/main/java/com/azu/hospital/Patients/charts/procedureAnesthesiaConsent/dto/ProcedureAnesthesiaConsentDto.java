package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dto;

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
public class ProcedureAnesthesiaConsentDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Procedure Anesthesia Consent Chart";

    private String dateOfVisit;

    private Long mrn;

    private String proposedProcedures;

    private String notEatenOrDrankSinceTime;

    private String notEatenOrDrankSinceDate;

    private String patientSignature;

    private String patientSignatureDate;

    private String witnessSignature;

    private String witnessSignatureDate;

    private String physicianSignature;

    private String physicianSignatureDate;

    private Long createdBy;
    private Long LastModifiedBy;


    private String title = "PRE OPERATIVE PROCEDURE/ANESTHESIA CONSENT";
    private String link = "procedure-anesthesia-consent";

    public ProcedureAnesthesiaConsentDto() {
    }

    public ProcedureAnesthesiaConsentDto(
            Long id,
            String chartName,
            String dateOfVisit,
            Long mrn,
            String proposedProcedures,
            String notEatenOrDrankSinceTime,
            String notEatenOrDrankSinceDate,
            String patientSignature,
            String patientSignatureDate,
            String witnessSignature,
            String witnessSignatureDate,
            String physicianSignature,
            String physicianSignatureDate,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.dateOfVisit = dateOfVisit;
        this.mrn = mrn;
        this.proposedProcedures = proposedProcedures;
        this.notEatenOrDrankSinceTime = notEatenOrDrankSinceTime;
        this.notEatenOrDrankSinceDate = notEatenOrDrankSinceDate;
        this.patientSignature = patientSignature;
        this.patientSignatureDate = patientSignatureDate;
        this.witnessSignature = witnessSignature;
        this.witnessSignatureDate = witnessSignatureDate;
        this.physicianSignature = physicianSignature;
        this.physicianSignatureDate = physicianSignatureDate;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
