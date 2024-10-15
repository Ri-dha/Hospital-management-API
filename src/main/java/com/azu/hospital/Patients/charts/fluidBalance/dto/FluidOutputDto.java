package com.azu.hospital.Patients.charts.fluidBalance.dto;

import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FieldFluidRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FluidOutputDto {
  private Long id;
  private String time;
  private FieldFluidRequest urine;
  private FieldFluidRequest bowelOrStoma;
  private FieldFluidRequest firstDrain;
  private FieldFluidRequest secondDrain;
  private FieldFluidRequest thirdDrain;
  private FieldFluidRequest fourDrain;
  private FieldFluidRequest runningOutputTotal;

  public FluidOutputDto() {
  }

  public FluidOutputDto(
          Long id,
          String time,
          FieldFluidRequest urine,
          FieldFluidRequest bowelOrStoma,
          FieldFluidRequest firstDrain,
          FieldFluidRequest secondDrain,
          FieldFluidRequest thirdDrain,
          FieldFluidRequest fourDrain,
          FieldFluidRequest runningOutputTotal
  ) {
    this.id = id;
    this.time = time;
    this.urine = urine;
    this.bowelOrStoma = bowelOrStoma;
    this.firstDrain = firstDrain;
    this.secondDrain = secondDrain;
    this.thirdDrain = thirdDrain;
    this.fourDrain = fourDrain;
    this.runningOutputTotal = runningOutputTotal;
  }
}
