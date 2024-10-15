package com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FluidByIntravenous {
  private BigDecimal firstFluidIntake;

  private BigDecimal secondFluidIntake;

  private BigDecimal thirdFluidIntake;

  private BigDecimal flush;

  private BigDecimal firstFluidIntakeTotal;

  private BigDecimal secondFluidIntakeTotal;

  private BigDecimal thirdFluidIntakeTotal;

  private BigDecimal flushTotal;

  public FluidByIntravenous() {
  }

  public FluidByIntravenous(
          BigDecimal firstFluidIntake,
          BigDecimal secondFluidIntake,
          BigDecimal thirdFluidIntake,
          BigDecimal flush,
          BigDecimal firstFluidIntakeTotal,
          BigDecimal secondFluidIntakeTotal,
          BigDecimal thirdFluidIntakeTotal,
          BigDecimal flushTotal
  ) {
    this.firstFluidIntake = firstFluidIntake;
    this.secondFluidIntake = secondFluidIntake;
    this.thirdFluidIntake = thirdFluidIntake;
    this.flush = flush;
    this.firstFluidIntakeTotal = firstFluidIntakeTotal;
    this.secondFluidIntakeTotal = secondFluidIntakeTotal;
    this.thirdFluidIntakeTotal = thirdFluidIntakeTotal;
    this.flushTotal = flushTotal;
  }
}
