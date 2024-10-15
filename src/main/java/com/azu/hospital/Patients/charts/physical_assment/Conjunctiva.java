package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Conjunctiva {
  @Column(name = "conjunctive_pink")
  private Boolean pink = false;
  @Column(name = "conjunctive_moist")
  private Boolean moist = false;
  @Column(name = "conjunctive_other")
  private String other;

  public Conjunctiva() {
  }

  public Conjunctiva(Boolean pink, Boolean moist, String other) {
    this.pink = pink;
    this.moist = moist;
    this.other = other;
  }
}
