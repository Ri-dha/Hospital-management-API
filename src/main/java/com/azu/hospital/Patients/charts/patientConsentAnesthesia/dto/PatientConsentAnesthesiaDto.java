package com.azu.hospital.Patients.charts.patientConsentAnesthesia.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PatientConsentAnesthesiaDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Patient Consent Anesthesia Chart";

    private String anesthesiaType;

    private String patientSignature;

    private String patientSignatureDate;

    private String patientSignatureTime;

    private String witnessSignature;

    private String witnessSignatureDate;

    private String witnessSignatureTime;

    private String anesthesiaProviderSignature;

    private String anesthesiaProviderSignatureDate;

    private String anesthesiaProviderSignatureTime;

    private Long createdBy;
    private Long LastModifiedBy;

    private String title = "PRE OPERATIVE PATIENT CONSENT ANESTHESIA";
    private String link = "patient-consent-anesthesia";

    public PatientConsentAnesthesiaDto() {
    }

    public PatientConsentAnesthesiaDto(
            Long id,
            String chartName,
            String anesthesiaType,
            String patientSignature,
            String patientSignatureDate,
            String patientSignatureTime,
            String witnessSignature,
            String witnessSignatureDate,
            String witnessSignatureTime,
            String anesthesiaProviderSignature,
            String anesthesiaProviderSignatureDate,
            String anesthesiaProviderSignatureTime,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.anesthesiaType = anesthesiaType;
        this.patientSignature = patientSignature;
        this.patientSignatureDate = patientSignatureDate;
        this.patientSignatureTime = patientSignatureTime;
        this.witnessSignature = witnessSignature;
        this.witnessSignatureDate = witnessSignatureDate;
        this.witnessSignatureTime = witnessSignatureTime;
        this.anesthesiaProviderSignature = anesthesiaProviderSignature;
        this.anesthesiaProviderSignatureDate = anesthesiaProviderSignatureDate;
        this.anesthesiaProviderSignatureTime = anesthesiaProviderSignatureTime;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;

    }
}
