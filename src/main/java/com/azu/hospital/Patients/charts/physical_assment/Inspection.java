package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class Inspection {
  private Boolean chestSymmetrical = false;
  private Boolean convexRound = false;

  public Inspection() {
  }

  public Inspection(Boolean chestSymmetrical, Boolean convexRound) {
    this.chestSymmetrical = chestSymmetrical;
    this.convexRound = convexRound;
  }
}
