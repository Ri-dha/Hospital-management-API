package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;


@Data
public class BreathSounds {
  private Boolean clear = false;
  private Boolean decreased = false;
  private Boolean crackles = false;
  private Boolean wheezing = false;
  private Boolean absent = false;

  public BreathSounds() {
  }

  public BreathSounds(Boolean clear, Boolean decreased, Boolean crackles, Boolean wheezing, Boolean absent) {
    this.clear = clear;
    this.decreased = decreased;
    this.crackles = crackles;
    this.wheezing = wheezing;
    this.absent = absent;
  }
}
