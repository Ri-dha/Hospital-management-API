package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class BowelSounds {
  private Boolean rlq = false;
  private Boolean luq = false;
  private Boolean ruq = false;
  private Boolean llq = false;


  public BowelSounds() {
  }

  public BowelSounds(Boolean rlq, Boolean luq, Boolean ruq, Boolean llq) {
    this.rlq = rlq;
    this.luq = luq;
    this.ruq = ruq;
    this.llq = llq;
  }
}
