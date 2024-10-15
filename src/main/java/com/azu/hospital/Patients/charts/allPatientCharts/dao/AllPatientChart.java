package com.azu.hospital.Patients.charts.allPatientCharts.dao;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.utils.enums.patient.ChartTypes;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "all_patient_charts")
@Data
public class AllPatientChart {
  @Id
  @SequenceGenerator(
          sequenceName = "all_patient_charts_id_sequence",
          name = "all_patient_charts_id_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "all_patient_charts_id_sequence"
  )
  private Long id;

//  @OneToOne
//  private AnesthesiaPhysicianOrders anesthesiaPhysicianOrders;
//
//  @OneToOne
//  private BurnDiagramChart burnDiagramChart;
//
//  @OneToOne
//  private FluidBalanceChart fluidBalanceChart;
//
//  @OneToOne
//  private NeurologicalObservationChart neurologicalObservationChart;
//
//  @OneToOne
//  private NonSedatedPatientAssessment nonSedatedPatientAssessment;
//
//  @OneToOne
//  private NursingAssessmentChart nursingAssessmentChart;
//
//  @OneToOne
//  private PainManagementChart painManagementChart;
//
//  @OneToOne
//  private PatientAssessmentChart patientAssessmentChart;
//
//  @OneToOne
//  private PatientConsentAnesthesiaChart patientConsentAnesthesiaChart;
//
//  @OneToOne
//  private PostAnestheticEvaluationChart postAnestheticEvaluationChart;
//
//  @OneToOne
//  private PostDischargeInstructionsChart postDischargeInstructionsChart;
//
//  @OneToOne
//  private PostOperativeFollowUpCall postOperativeFollowUpCall;
//
//  @OneToOne
//  private PreAdvanceDirectiveChart preAdvanceDirectiveChart;
//
//  @OneToOne
//  private PreAnestheticEvaluationChart preAnestheticEvaluationChart;
//
//  @OneToOne
//  private PreMedicalAssessmentChart preMedicalAssessmentChart;
//
//  @OneToOne
//  private PreOperativeHPChart preOperativeHPChart;
//
//  @OneToOne
//  private ProcedureAnesthesiaConsentChart procedureAnesthesiaConsentChart;
//
//  @OneToOne
//  private SedatedPatientAssessment sedatedPatientAssessment;

//  private Boolean anesthesiaPhysicianOrders = false;
//
//  private Boolean burnDiagramChart = false;
//
//  private Boolean fluidBalanceChart = false;
//
//  private Boolean neurologicalObservationChart = false;
//
//  private Boolean nonSedatedPatientAssessment = false;
//
//  private Boolean nursingAssessmentChart = false;
//
//  private Boolean painManagementChart = false;
//
//  private Boolean patientAssessmentChart = false;
//
//  private Boolean patientConsentAnesthesiaChart = false;
//
//  private Boolean postAnestheticEvaluationChart = false;
//
//  private Boolean postDischargeInstructionsChart = false;
//
//  private Boolean postOperativeFollowUpCall = false;
//
//  private Boolean preAdvanceDirectiveChart = false;
//
//  private Boolean preAnestheticEvaluationChart = false;
//
//  private Boolean preMedicalAssessmentChart = false;
//
//  private Boolean preOperativeHPChart = false;
//
//  private Boolean procedureAnesthesiaConsentChart = false;
//
//  private Boolean sedatedPatientAssessment = false;

  private ChartTypes chartType;

  @OneToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @Column(updatable = false)
  private Instant createdAt;

  private Instant updatedAt;

  public AllPatientChart() {
  }

  @PrePersist
  public void onCreate(){
    this.createdAt = Instant.now();
  }
  @PreUpdate
  public void onUpdate(){
    this.updatedAt = Instant.now();
  }

}
