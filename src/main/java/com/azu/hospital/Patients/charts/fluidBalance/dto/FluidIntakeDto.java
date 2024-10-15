package com.azu.hospital.Patients.charts.fluidBalance.dto;

import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FieldFluidRequest;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FluidByIntravenous;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.Oral;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FluidIntakeDto {
  private Long fluidBalanceInputId;

  private String time;

  private Oral oral;

  private FluidByIntravenous fluidByIntravenous;

  private FieldFluidRequest nasogastricFeed;

  private FieldFluidRequest hourlyTotal;

  private FieldFluidRequest runningInputTotal;

  public FluidIntakeDto() {
  }

  public FluidIntakeDto(
          Long fluidBalanceInputId,
          String time,
          Oral oral,
          FieldFluidRequest nasogastricFeed,
          FluidByIntravenous fluidByIntravenous,
          FieldFluidRequest hourlyTotal,
          FieldFluidRequest runningInputTotal
  ) {
    this.fluidBalanceInputId = fluidBalanceInputId;
    this.time = time;
    this.oral = oral;
    this.fluidByIntravenous = fluidByIntravenous;
    this.nasogastricFeed = nasogastricFeed;
    this.hourlyTotal = hourlyTotal;
    this.runningInputTotal = runningInputTotal;
  }
}
