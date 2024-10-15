package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class Moisture {
  private Boolean clammy = false;
  private Boolean diaphoretic = false;
  private Boolean dry = false;

  public Moisture() {
  }

  public Moisture(Boolean clammy, Boolean diaphoretic, Boolean dry) {
    this.clammy = clammy;
    this.diaphoretic = diaphoretic;
    this.dry = dry;
  }
}
