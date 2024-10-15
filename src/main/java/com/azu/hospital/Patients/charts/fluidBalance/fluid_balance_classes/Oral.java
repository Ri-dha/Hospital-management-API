package com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Oral {
  private String type;
  private BigDecimal volume;
  private BigDecimal total;

  public Oral() {
  }
  public Oral(String type, BigDecimal volume, BigDecimal total) {
    this.type = type;
    this.volume = volume;
    this.total = total;
  }
}
