package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LowerExtremitiesStates {
  @Column(name = "lower_strong")
  private Boolean strong = false;
  @Column(name = "lower_weak")
  private Boolean weak = false;
  @Column(name = "lower_unable")
  private Boolean unable = false;

  public LowerExtremitiesStates() {
  }

  public LowerExtremitiesStates(Boolean strong, Boolean weak, Boolean unable) {
    this.strong = strong;
    this.weak = weak;
    this.unable = unable;
  }
}
