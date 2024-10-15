package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.*;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CardiacRiskAssessment {
  private RCRIRisk rcriRisk;

  private PatientCardiacRisk patientHas;

  @Column(columnDefinition = "TEXT")
  private String nonCardiacRisk;

  private Boolean furtherConsults = false;

  private Boolean furtherTesting = false;
  @Column(columnDefinition = "TEXT")
  private String overallMedicalRisk;
  @Column(columnDefinition = "TEXT")
  private String recommendations;
  private OptimizedRiskAssessment optimized;

  public CardiacRiskAssessment() {
  }

  public CardiacRiskAssessment(
          RCRIRisk rcriRisk,
          PatientCardiacRisk patientHas,
          String nonCardiacRisk,
          Boolean furtherConsults,
          Boolean furtherTesting,
          String overallMedicalRisk,
          String recommendations,
          OptimizedRiskAssessment optimized
  ) {
    this.rcriRisk = rcriRisk;
    this.patientHas = patientHas;
    this.nonCardiacRisk = nonCardiacRisk;
    this.furtherConsults = furtherConsults;
    this.furtherTesting = furtherTesting;
    this.overallMedicalRisk = overallMedicalRisk;
    this.recommendations = recommendations;
    this.optimized = optimized;
  }
}
