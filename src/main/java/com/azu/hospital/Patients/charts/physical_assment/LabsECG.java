package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class LabsECG {
  private String hh;
  private String ptt;
  private String lytes;
  private String ecg;

  public LabsECG() {
  }

  public LabsECG(String hh, String ptt, String lytes, String ecg) {
    this.hh = hh;
    this.ptt = ptt;
    this.lytes = lytes;
    this.ecg = ecg;
  }
}
