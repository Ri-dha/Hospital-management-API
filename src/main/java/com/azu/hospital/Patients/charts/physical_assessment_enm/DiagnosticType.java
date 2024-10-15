package com.azu.hospital.Patients.charts.physical_assessment_enm;

public enum DiagnosticType {
  X_RAY("X-Ray"),
  CT_SCAN("CT Scan"),
  MRI("MRI"),
  EMG("EMG"),
  MYELOGRAM("Myelogram"),
  THERMOGRAM("Thermogram"),
  EPIDUROGRAM("Epidurogram"),
  LABORATORY_TESTS("Laboratory tests");

  private final String displayName;

  DiagnosticType(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
