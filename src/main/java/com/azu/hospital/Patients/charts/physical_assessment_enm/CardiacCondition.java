package com.azu.hospital.Patients.charts.physical_assessment_enm;

public enum CardiacCondition {
  PULMONARY_HYPERTENSION("Pulmonary Hypertension (latest PAP ___)"),
  CONGENITAL_HEART_DISEASE("Congenital heart disease"),
  MI("MI (yes, no, <30d)"),
  PCI_STENT("PCI Stent (bare / drug)"),
  ANGINA("Angina (unstable / stable / severe)"),
  HTN("HTN (yes, no, controlled)"),
  CAD("CAD (History of abnormal stress test or Q waves on EKG)"),
  CHRONIC_CHF("Chronic systolic/diastolic CHF (compensated / not)"),
  CARDIOMYOPATHY("Cardiomyopathy"),
  VALVE_DISEASE("Valve disease (symptomatic / severe AS, MS, MR)"),
  ARRHYTHMIA("Arrhythmia"),
  PM_ICD("PM/ICD (manufacturer, indication, device settings, when last interrogated)");

  private final String description;

  CardiacCondition(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}

