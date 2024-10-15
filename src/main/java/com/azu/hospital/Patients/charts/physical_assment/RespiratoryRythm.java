package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RespiratoryRythm {
  private Boolean rythmUnlabored = false;
  @Column(name = "rythmShallow")
  private Boolean shallow = false;
  @Column(name = "rythm_deep")
  private Boolean deep = false;

  public RespiratoryRythm() {
  }

  public RespiratoryRythm(Boolean rythmUnlabored, Boolean shallow, Boolean deep) {
    this.rythmUnlabored = rythmUnlabored;
    this.shallow = shallow;
    this.deep = deep;
  }
}
