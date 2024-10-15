package com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FieldFluidRequest {

  private BigDecimal value;
  private BigDecimal total;

  public FieldFluidRequest() {
  }

  public FieldFluidRequest(BigDecimal value, BigDecimal total) {
    this.value = value;
    this.total = total;
  }
}
