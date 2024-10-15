package com.azu.hospital.Patients.charts.fluidBalance.dto;

import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidIntake;
import org.springframework.stereotype.Service;

@Service
public class FluidIntakeDtoMapper {
  public FluidIntakeDto chartToDto(FluidIntake fluidIntake){
    return new FluidIntakeDto(
            fluidIntake.getFluidIntakeId(),
            fluidIntake.getTime(),
            fluidIntake.getOral(),
            fluidIntake.getNasogastricFeed(),
            fluidIntake.getFluidByIntravenous(),
            fluidIntake.getHourlyTotal(),
            fluidIntake.getRunningInputTotal()
    );
  }
}
