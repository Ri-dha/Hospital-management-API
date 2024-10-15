package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class HeartSounds {
  private Boolean normal = false;
  private Boolean murmur = false;
  private Boolean gallop = false;
  private Boolean distant = false;
  private Boolean muffled = false;
  private Boolean extraSounds = false;

  public HeartSounds() {
  }

  public HeartSounds(Boolean normal, Boolean murmur, Boolean gallop, Boolean distant, Boolean muffled, Boolean extraSounds) {
    this.normal = normal;
    this.murmur = murmur;
    this.gallop = gallop;
    this.distant = distant;
    this.muffled = muffled;
    this.extraSounds = extraSounds;
  }
}
