package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class Palpation {
  private Boolean soft = false;
  private Boolean tender = false;
  private Boolean firm = false;
  private Boolean hard = false;
  private Boolean guardingPain = false;
  private Boolean distended = false;
  private Boolean flat = false;
  private Boolean rigid = false;

  public Palpation() {
  }

  public Palpation(Boolean soft, Boolean tender, Boolean firm, Boolean hard,
                          Boolean guardingPain, Boolean distended, Boolean flat, Boolean rigid) {
    this.soft = soft;
    this.tender = tender;
    this.firm = firm;
    this.hard = hard;
    this.guardingPain = guardingPain;
    this.distended = distended;
    this.flat = flat;
    this.rigid = rigid;
  }
}
