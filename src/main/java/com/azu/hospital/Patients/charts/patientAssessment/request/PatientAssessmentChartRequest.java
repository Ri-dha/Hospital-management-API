package com.azu.hospital.Patients.charts.patientAssessment.request;

import com.azu.hospital.Patients.charts.physical_assessment_enm.PreProcedureChecklist;
import com.azu.hospital.Patients.charts.physical_assessment_enm.SkinCondition;
import com.azu.hospital.Patients.charts.physical_assment.IVSite;
import com.azu.hospital.Patients.charts.physical_assment.PatientLOC;
import lombok.Data;

import java.util.Map;

@Data
public class PatientAssessmentChartRequest {
  private String diagnosis;
  private String procedures;
  private SkinCondition skinCondition;
  private Map<String, Boolean> physicalLimitations;
  private PatientLOC loc;
  private IVSite ivSite;

  private Map<PreProcedureChecklist, Boolean> preProcedureChecklist;

  public PatientAssessmentChartRequest() {
  }

  public PatientAssessmentChartRequest(
          String diagnosis,
          String procedures,
          SkinCondition skinCondition,
          Map<String, Boolean> physicalLimitations,
          PatientLOC loc,
          IVSite ivSite,
          Map<PreProcedureChecklist, Boolean> preProcedureChecklist

  ) {
    this.diagnosis = diagnosis;
    this.procedures = procedures;
    this.skinCondition = skinCondition;
    this.physicalLimitations = physicalLimitations;
    this.loc = loc;
    this.ivSite = ivSite;
    this.preProcedureChecklist = preProcedureChecklist;
  }
}

