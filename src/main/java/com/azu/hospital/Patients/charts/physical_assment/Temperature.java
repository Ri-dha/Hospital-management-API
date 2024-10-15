package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class Temperature {
  private Boolean hot = false;
  private Boolean warm = false;
  private Boolean cool = false;
  private Boolean cold = false;

  public Temperature() {
  }

  public Temperature(Boolean hot, Boolean warm, Boolean cool, Boolean cold) {
    this.hot = hot;
    this.warm = warm;
    this.cool = cool;
    this.cold = cold;
  }
}
