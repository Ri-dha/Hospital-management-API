package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class UpperExtremitiesStates {
  private Boolean strong = false;
  private Boolean weak = false;
  private Boolean unable = false;

  public UpperExtremitiesStates() {
  }

  public UpperExtremitiesStates(Boolean strong, Boolean weak, Boolean unable) {
    this.strong = strong;
    this.weak = weak;
    this.unable = unable;
  }
}
