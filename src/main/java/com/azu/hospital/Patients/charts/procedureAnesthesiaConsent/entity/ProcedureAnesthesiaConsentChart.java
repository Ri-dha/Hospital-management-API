package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Table(name = "procedure_anesthesia_consent_chart")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ProcedureAnesthesiaConsentChart extends BaseCharts {

  private String chartName ="Procedure Anesthesia Consent Chart";
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
  public ProcedureAnesthesiaConsentChart() {
  }

  public ProcedureAnesthesiaConsentChart(Builder builder) {
    this.dateOfVisit = builder.chart.dateOfVisit;
    this.mrn = builder.chart.mrn;
    this.proposedProcedures = builder.chart.proposedProcedures;
    this.notEatenOrDrankSinceTime = builder.chart.notEatenOrDrankSinceTime;
    this.notEatenOrDrankSinceDate = builder.chart.notEatenOrDrankSinceDate;
    this.patientSignature = builder.chart.patientSignature;
    this.patientSignatureDate = builder.chart.patientSignatureDate;
    this.witnessSignature = builder.chart.witnessSignature;
    this.witnessSignatureDate = builder.chart.witnessSignatureDate;
    this.physicianSignature = builder.chart.physicianSignature;
    this.physicianSignatureDate = builder.chart.physicianSignatureDate;
  }

  public static class Builder{
    private final ProcedureAnesthesiaConsentChart chart = new ProcedureAnesthesiaConsentChart();
    public Builder withDateOfVisit(String dateOfVisit){
      chart.dateOfVisit = dateOfVisit;
      return this;
    }
    public Builder withMrn(Long mrn){
      chart.mrn = mrn;
      return this;
    }
    public Builder withProposedProcedures(String proposedProcedures){
      chart.proposedProcedures = proposedProcedures;
      return this;
    }
    public Builder withNotEatenOrDrankSinceTime(String notEatenOrDrankSinceTime){
      chart.notEatenOrDrankSinceTime = notEatenOrDrankSinceTime;
      return this;
    }
    public Builder withNotEatenOrDrankSinceDate(String notEatenOrDrankSinceDate){
      chart.notEatenOrDrankSinceDate = notEatenOrDrankSinceDate;
      return this;
    }
    public Builder withPatientSignature(String patientSignature){
      chart.patientSignature = patientSignature;
      return this;
    }
    public Builder withPatientSignatureDate(String patientSignatureDate){
      chart.patientSignatureDate = patientSignatureDate;
      return this;
    }
    public Builder withWitnessSignature(String witnessSignature){
      chart.witnessSignature = witnessSignature;
      return this;
    }
    public Builder withWitnessSignatureDate(String witnessSignatureDate){
      chart.witnessSignatureDate = witnessSignatureDate;
      return this;
    }
    public Builder withPhysicianSignature(String physicianSignature){
      chart.physicianSignature = physicianSignature;
      return this;
    }
    public Builder withPhysicianSignatureDate(String physicianSignatureDate){
      chart.physicianSignatureDate = physicianSignatureDate;
      return this;
    }
    public ProcedureAnesthesiaConsentChart build(){
      return new ProcedureAnesthesiaConsentChart(this);
    }
  }

}
