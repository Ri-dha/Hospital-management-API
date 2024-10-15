package com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assment.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sedated_patient_assessment_chart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class SedatedPatientAssessment extends BaseCharts {

  private String chartName = "Sedated Patient Assessment Chart";
  @Column(columnDefinition = "TEXT")
  private String medications;

  @Column(columnDefinition = "TEXT")
  private String positiveProblems;

  @Column(columnDefinition = "TEXT")
  private String preOpMeds;

  private String asa;

  @Embedded
  private PreProcedure preProcedure;

  @Embedded
  private PreAnesthesiaState preAnesthesiaState;

  @Embedded
  private PatientSafety patientSafety;

  @Embedded
  private EyeCare eyeCare;

  @Embedded
  private AnestheticTechnique anestheticTechnique;

  @Embedded
  private MonitorsEquipment monitorsEquipment;

  @Embedded
  private AirwayManagement airwayManagement;

  private String anesthesiaStartTime;

  private String anesthesiaEndTime;

  private String procedureStartTime;

  private String procedureEndTime;

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
//  @OneToMany(
//          cascade = CascadeType.ALL,
//          fetch = FetchType.LAZY
//  )
//  @JsonIgnoreProperties("chart")
//  private List<ProcedureTable> procedureTable;

  public SedatedPatientAssessment() {
  }
  public SedatedPatientAssessment(Builder builder) {
    this.medications = builder.chart.medications;
    this.positiveProblems = builder.chart.positiveProblems;
    this.preOpMeds = builder.chart.preOpMeds;
    this.asa = builder.chart.asa;
    this.preProcedure = builder.chart.preProcedure;
    this.preAnesthesiaState = builder.chart.preAnesthesiaState;
    this.patientSafety = builder.chart.patientSafety;
    this.eyeCare = builder.chart.eyeCare;
    this.anestheticTechnique = builder.chart.anestheticTechnique;
    this.monitorsEquipment = builder.chart.monitorsEquipment;
    this.airwayManagement = builder.chart.airwayManagement;
    this.anesthesiaStartTime = builder.chart.anesthesiaStartTime;
    this.anesthesiaEndTime = builder.chart.anesthesiaEndTime;
    this.procedureStartTime = builder.chart.procedureStartTime;
    this.procedureEndTime = builder.chart.procedureEndTime;
  }

  public static class Builder{
    private final SedatedPatientAssessment chart = new SedatedPatientAssessment();

    public Builder withMedications(String medications){
      chart.medications = medications;
      return this;
    }
    public Builder withPositiveProblems(String positiveProblems){
      chart.positiveProblems = positiveProblems;
      return this;
    }
    public Builder withPreOpMeds(String preOpMeds){
      chart.preOpMeds = preOpMeds;
      return this;
    }
    public Builder withAsa(String asa){
      chart.asa = asa;
      return this;
    }
    public Builder withPreProcedure(PreProcedure preProcedure){
      chart.preProcedure = preProcedure;
      return this;
    }
    public Builder withPreAnesthesiaState(PreAnesthesiaState preAnesthesiaState){
      chart.preAnesthesiaState = preAnesthesiaState;
      return this;
    }
    public Builder withPatientSafety(PatientSafety patientSafety){
      chart.patientSafety = patientSafety;
      return this;
    }
    public Builder withEyeCare(EyeCare eyeCare){
      chart.eyeCare = eyeCare;
      return this;
    }
    public Builder withAnestheticTechnique(AnestheticTechnique anestheticTechnique){
      chart.anestheticTechnique = anestheticTechnique;
      return this;
    }
    public Builder withMonitorsEquipment(MonitorsEquipment monitorsEquipment){
      chart.monitorsEquipment = monitorsEquipment;
      return this;
    }
    public Builder withAirwayManagement(AirwayManagement airwayManagement){
      chart.airwayManagement = airwayManagement;
      return this;
    }
    public Builder withAnesthesiaStartTime(String anesthesiaStartTime){
      chart.anesthesiaStartTime = anesthesiaStartTime;
      return this;
    }
    public Builder withAnesthesiaEndTime(String anesthesiaEndTime){
      chart.anesthesiaEndTime = anesthesiaEndTime;
      return this;
    }
    public Builder withProcedureStartTime(String procedureStartTime){
      chart.procedureStartTime = procedureStartTime;
      return this;
    }
    public Builder withProcedureEndTime(String procedureEndTime){
      chart.procedureEndTime = procedureEndTime;
      return this;
    }
    public SedatedPatientAssessment build(){
      return new SedatedPatientAssessment(this);
    }
  }

}
