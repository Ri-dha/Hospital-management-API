package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class Pupils {
  private Boolean equal = false;
  private Boolean round = false;
  private Boolean accommodating = false;
  private Boolean reactiveToLight = false;

  public Pupils() {
  }

  public Pupils(Boolean equal, Boolean round, Boolean accommodating, Boolean reactiveToLight) {
    this.equal = equal;
    this.round = round;
    this.accommodating = accommodating;
    this.reactiveToLight = reactiveToLight;
  }
}
