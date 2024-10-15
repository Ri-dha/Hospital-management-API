package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.HighRiskCardiacCondition;
import com.azu.hospital.Patients.charts.physical_assessment_enm.RiskLevel;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.Map;

@Data
public class SurgicalRisk {
  RiskLevel riskLevel;

  @ElementCollection(fetch = FetchType.EAGER)
  Map<HighRiskCardiacCondition, Boolean> highRiskCardiacCondition;

  public SurgicalRisk() {
  }

  public SurgicalRisk(
          RiskLevel riskLevel,
          Map<HighRiskCardiacCondition, Boolean> highRiskCardiacCondition
  ) {
    this.riskLevel = riskLevel;
    this.highRiskCardiacCondition = highRiskCardiacCondition;
  }
}
