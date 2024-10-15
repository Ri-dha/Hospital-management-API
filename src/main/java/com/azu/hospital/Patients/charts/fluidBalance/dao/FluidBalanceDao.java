package com.azu.hospital.Patients.charts.fluidBalance.dao;

import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidBalanceChart;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidIntake;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FluidBalanceDao {
  FluidBalanceChart createNewChart(FluidBalanceChart chart);

  FluidBalanceChart updateExistsChart(FluidBalanceChart update);

  Optional<FluidBalanceChart> findChartById(Long chartId);

  Page<FluidBalanceChart> getAllChart(Pageable pageable);

  FluidIntake createNewInputRow(FluidIntake input);
  FluidIntake updateExistsInputRow(FluidIntake update);
  Optional<FluidIntake> getInputRowById(Long rowId);

  FluidOutput createNewOutputRow(FluidOutput input);
  FluidOutput updateExistsOutputRow(FluidOutput update);
  Optional<FluidOutput> getOutputRowById(Long rowId);

  List<FluidBalanceChart> getAllChartByPatientId(Long patientId);
}
