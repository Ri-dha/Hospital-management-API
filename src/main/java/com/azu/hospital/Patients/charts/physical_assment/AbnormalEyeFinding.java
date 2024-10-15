package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class AbnormalEyeFinding {
  private Boolean pinpoint = false;
  private Boolean fixed = false;
  private Boolean unequal = false;

  public AbnormalEyeFinding() {
  }

  public AbnormalEyeFinding(Boolean pinpoint, Boolean fixed, Boolean unequal) {
    this.pinpoint = pinpoint;
    this.fixed = fixed;
    this.unequal = unequal;
  }
}
