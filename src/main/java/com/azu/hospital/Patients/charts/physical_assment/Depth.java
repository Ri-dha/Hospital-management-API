package com.azu.hospital.Patients.charts.physical_assment;

import lombok.Data;

@Data
public class Depth {
  private Boolean unlabored = false;
  private Boolean shallow = false;
  private Boolean deep = false;

  public Depth() {
  }

  public Depth(Boolean unlabored, Boolean shallow, Boolean deep) {
    this.unlabored = unlabored;
    this.shallow = shallow;
    this.deep = deep;
  }
}
