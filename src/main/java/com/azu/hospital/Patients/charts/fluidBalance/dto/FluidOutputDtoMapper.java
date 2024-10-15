package com.azu.hospital.Patients.charts.fluidBalance.dto;

import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidOutput;
import org.springframework.stereotype.Service;

@Service
public class FluidOutputDtoMapper {
  public FluidOutputDto chartToDto(FluidOutput chart){
    return new FluidOutputDto(
            chart.getFluidOutputId(),
            chart.getTime(),
            chart.getUrine(),
            chart.getBowelOrStoma(),
            chart.getFirstDrain(),
            chart.getSecondDrain(),
            chart.getThirdDrain(),
            chart.getFourDrain(),
            chart.getRunningOutputTotal()
    );
  }
}
