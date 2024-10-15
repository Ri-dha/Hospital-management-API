package com.azu.hospital.Patients.charts.nusringCarePlan.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NursingCarePlanRequest {
  private String nursingDiagnosis;

  private String goalsAndOutComes;

  private String intervensions;

  private String evaluation;

  public NursingCarePlanRequest(String nursingDiagnosis, String goalsAndOutComes, String intervensions, String evaluation) {
    this.nursingDiagnosis = nursingDiagnosis;
    this.goalsAndOutComes = goalsAndOutComes;
    this.intervensions = intervensions;
    this.evaluation = evaluation;
  }
}
