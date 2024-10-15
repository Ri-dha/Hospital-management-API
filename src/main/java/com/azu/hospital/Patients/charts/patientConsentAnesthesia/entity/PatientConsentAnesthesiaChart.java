package com.azu.hospital.Patients.charts.patientConsentAnesthesia.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "patient_consent_anesthesia_chart")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class PatientConsentAnesthesiaChart extends BaseCharts {

    private String chartName = "Patient Consent Anesthesia Chart";
    private String title = "PRE OPERATIVE PATIENT CONSENT ANESTHESIA";
    private String link = "patient-consent-anesthesia";
    private String anesthesiaType;

    private String patientSignature;

    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Patient Signature Date: Date Should be Like YYYY-MM-DD"
    )
    private String patientSignatureDate;

    @Pattern(
            regexp = "^(?:[01][0-9]|2[0-3]):[0-5][0-9]$",
            message = "Patient Signature Time: Invalid time format (HH:MM)"
    )
    private String patientSignatureTime;

    private String witnessSignature;

    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Witness Signature Date: Date Should be Like YYYY-MM-DD"
    )
    private String witnessSignatureDate;

    @Pattern(
            regexp = "^(?:[01][0-9]|2[0-3]):[0-5][0-9]$",
            message = "Witness Signature Time: Invalid time format (HH:MM)"
    )
    private String witnessSignatureTime;

    private String anesthesiaProviderSignature;

    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Anesthesia Provider Signature Date: Date Should be Like YYYY-MM-DD"
    )
    private String anesthesiaProviderSignatureDate;

    @Pattern(
            regexp = "^(?:[01][0-9]|2[0-3]):[0-5][0-9]$",
            message = "Anesthesia Provider Signature Time: Invalid time format (HH:MM)"
    )
    private String anesthesiaProviderSignatureTime;


    @CreatedBy
    @Column(
            updatable = false
    )
    private Long createdBy;
    @LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;

    public PatientConsentAnesthesiaChart() {
    }


    public PatientConsentAnesthesiaChart(Builder builder) {
        this.anesthesiaType = builder.chart.anesthesiaType;
        this.patientSignature = builder.chart.patientSignature;
        this.patientSignatureDate = builder.chart.patientSignatureDate;
        this.patientSignatureTime = builder.chart.patientSignatureTime;
        this.witnessSignature = builder.chart.witnessSignature;
        this.witnessSignatureDate = builder.chart.witnessSignatureDate;
        this.witnessSignatureTime = builder.chart.witnessSignatureTime;
        this.anesthesiaProviderSignature = builder.chart.anesthesiaProviderSignature;
        this.anesthesiaProviderSignatureDate = builder.chart.anesthesiaProviderSignatureDate;
        this.anesthesiaProviderSignatureTime = builder.chart.anesthesiaProviderSignatureTime;
    }


    public static class Builder {
        private final PatientConsentAnesthesiaChart chart = new PatientConsentAnesthesiaChart();

        public Builder withAnesthesiaType(String anesthesiaType) {
            chart.anesthesiaType = anesthesiaType;
            return this;
        }

        public Builder withPatientSignature(String patientSignature) {
            chart.patientSignature = patientSignature;
            return this;
        }

        public Builder withPatientSignatureDate(String patientSignatureDate) {
            chart.patientSignatureDate = patientSignatureDate;
            return this;
        }

        public Builder withPatientSignatureTime(String patientSignatureTime) {
            chart.patientSignatureTime = patientSignatureTime;
            return this;
        }

        public Builder withWitnessSignature(String witnessSignature) {
            chart.witnessSignature = witnessSignature;
            return this;
        }

        public Builder withWitnessSignatureDate(String witnessSignatureDate) {
            chart.witnessSignatureDate = witnessSignatureDate;
            return this;
        }

        public Builder withWitnessSignatureTime(String witnessSignatureTime) {
            chart.witnessSignatureTime = witnessSignatureTime;
            return this;
        }

        public Builder withAnesthesiaProviderSignature(String anesthesiaProviderSignature) {
            chart.anesthesiaProviderSignature = anesthesiaProviderSignature;
            return this;
        }

        public Builder withAnesthesiaProviderSignatureDate(String anesthesiaProviderSignatureDate) {
            chart.anesthesiaProviderSignatureDate = anesthesiaProviderSignatureDate;
            return this;
        }

        public Builder withAnesthesiaProviderSignatureTime(String anesthesiaProviderSignatureTime) {
            chart.anesthesiaProviderSignatureTime = anesthesiaProviderSignatureTime;
            return this;
        }

        public PatientConsentAnesthesiaChart build() {
            return new PatientConsentAnesthesiaChart(this);
        }
    }


}
