package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class RythmState {
  private Boolean regular = false;
  private Boolean irregular = false;

  public RythmState() {
  }

  public RythmState(Boolean regular, Boolean irregular) {
    this.regular = regular;
    this.irregular = irregular;
  }
}
