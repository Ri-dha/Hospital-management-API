package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AbdomenInspection {
  @Column(name = "abdomen_symmetrical")
  private Boolean symmetrical = false;
  private Boolean rounded = false;
  @Column(name = "abdomen_other")
  private String other;

  public AbdomenInspection() {
  }

  public AbdomenInspection(Boolean symmetrical, Boolean rounded, String other) {
    this.symmetrical = symmetrical;
    this.rounded = rounded;
    this.other = other;
  }
}
