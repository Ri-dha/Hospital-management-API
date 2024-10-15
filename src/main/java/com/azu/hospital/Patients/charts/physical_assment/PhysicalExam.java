package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class PhysicalExam {
  private String airway;
  private String cardiac;
  private String lungs;
  private String other;

  public PhysicalExam() {
  }

  public PhysicalExam(String airway, String cardiac, String lungs, String other) {
    this.airway = airway;
    this.cardiac = cardiac;
    this.lungs = lungs;
    this.other = other;
  }
}
