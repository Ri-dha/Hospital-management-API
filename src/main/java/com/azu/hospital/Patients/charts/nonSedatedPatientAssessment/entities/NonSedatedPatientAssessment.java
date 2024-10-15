package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities;


import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assment.MedsPerMD;
import com.azu.hospital.Patients.charts.physical_assment.PatientLimitation;
import com.azu.hospital.Patients.charts.physical_assment.PatientPositions;
import com.azu.hospital.Patients.charts.physical_assment.ProcedureArea;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "non_sedated_patient_assessment")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class NonSedatedPatientAssessment extends BaseCharts {


  private String chartName="Non Sedated Patient Assessment";

  @Embedded
  private PatientPositions patientPositions;

  @Embedded
  private PatientLimitation patientLimitation;

  @Embedded
  private ProcedureArea procedureArea;

  @Embedded
  private MedsPerMD medsPerMD;

  @OneToMany
  private Set<MedsPerMDEntity> medsPerMDList = new HashSet<>();

  private String medsPerMDListOther;

  @Column(columnDefinition = "TEXT")
  private String nursesNotes;

  @Column(columnDefinition = "TEXT")
  private String postOperativeDiagnosis;

  @Column(columnDefinition = "TEXT")
  private String procedurePerformed;

  private String timeStart;

  private String timeFinish;

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



  public NonSedatedPatientAssessment() {
  }

  public NonSedatedPatientAssessment(Builder builder) {
    this.patientPositions = builder.chart.patientPositions;
    this.patientLimitation = builder.chart.patientLimitation;
    this.procedureArea = builder.chart.procedureArea;
    this.medsPerMD = builder.chart.medsPerMD;
    this.medsPerMDList = builder.chart.medsPerMDList;
    this.medsPerMDListOther = builder.chart.medsPerMDListOther;
    this.nursesNotes = builder.chart.nursesNotes;
    this.procedurePerformed = builder.chart.procedurePerformed;
    this.postOperativeDiagnosis = builder.chart.postOperativeDiagnosis;
    this.timeStart = builder.chart.timeStart;
    this.timeFinish = builder.chart.timeFinish;
  }

  public static class Builder {
    private final NonSedatedPatientAssessment chart = new NonSedatedPatientAssessment();

    public Builder withPatientPositions(PatientPositions patientPositions) {
      chart.patientPositions = patientPositions;
      return this;
    }

    public Builder withPatientLimitation(PatientLimitation patientLimitation) {
      chart.patientLimitation = patientLimitation;
      return this;
    }

    public Builder withProcedureArea(ProcedureArea procedureArea) {
      chart.procedureArea = procedureArea;
      return this;
    }

    public Builder withMedsPerMD(MedsPerMD medsPerMD) {
      chart.medsPerMD = medsPerMD;
      return this;
    }

    public Builder withMedsPerMDListOther(String medsPerMDListOther) {
      chart.medsPerMDListOther = medsPerMDListOther;
      return this;
    }

    public Builder withNursesNotes(String nursesNotes) {
      chart.nursesNotes = nursesNotes;
      return this;
    }

    public Builder withPostOperativeDiagnosis(String postOperativeDiagnosis) {
      chart.postOperativeDiagnosis = postOperativeDiagnosis;
      return this;
    }

    public Builder withProcedurePerformed(String procedurePerformed) {
      chart.procedurePerformed = procedurePerformed;
      return this;
    }

    public Builder withTimeStart(String timeStart) {
      chart.timeStart = timeStart;
      return this;
    }

    public Builder withTimeFinish(String timeFinish) {
      chart.timeFinish = timeFinish;
      return this;
    }

    public NonSedatedPatientAssessment build() {
      return new NonSedatedPatientAssessment(this);
    }
  }


}
