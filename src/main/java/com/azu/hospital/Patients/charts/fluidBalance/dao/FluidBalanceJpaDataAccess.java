package com.azu.hospital.Patients.charts.fluidBalance.dao;

import com.azu.hospital.Patients.charts.fluidBalance.dao.repository.FluidInputJpaRepository;
import com.azu.hospital.Patients.charts.fluidBalance.dao.repository.FluidBalanceJpaRepository;
import com.azu.hospital.Patients.charts.fluidBalance.dao.repository.FluidOutputJpaRepository;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidBalanceChart;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidIntake;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("FluidBalanceJpaDataAccess")
public class FluidBalanceJpaDataAccess implements FluidBalanceDao{

  private final FluidBalanceJpaRepository repository;
  private final FluidInputJpaRepository inputTableRepository;
  private final FluidOutputJpaRepository outputRepository;

  @Autowired
  public FluidBalanceJpaDataAccess(
          FluidBalanceJpaRepository repository,
          FluidInputJpaRepository inputTableRepository,
          FluidOutputJpaRepository outputRepository
  ) {
    this.repository = repository;
    this.inputTableRepository = inputTableRepository;
    this.outputRepository = outputRepository;
  }

  @Override
  public FluidBalanceChart createNewChart(FluidBalanceChart chart) {
    return repository.save(chart);
  }

  @Override
  public FluidBalanceChart updateExistsChart(FluidBalanceChart update) {
    return repository.save(update);
  }

  @Override
  public Optional<FluidBalanceChart> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public Page<FluidBalanceChart> getAllChart(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public FluidIntake createNewInputRow(FluidIntake input) {
    return inputTableRepository.save(input);
  }

  @Override
  public FluidIntake updateExistsInputRow(FluidIntake update) {
    return inputTableRepository.save(update);
  }

  @Override
  public Optional<FluidIntake> getInputRowById(Long rowId) {
    return inputTableRepository.findById(rowId);
  }

  @Override
  public FluidOutput createNewOutputRow(FluidOutput output) {
    return outputRepository.save(output);
  }

  @Override
  public FluidOutput updateExistsOutputRow(FluidOutput update) {
    return outputRepository.save(update);
  }

  @Override
  public Optional<FluidOutput> getOutputRowById(Long rowId) {
    return outputRepository.findById(rowId);
  }

  @Override
  public List<FluidBalanceChart> getAllChartByPatientId(Long patientId) {
    return repository.findAllByPatientId(patientId);
  }

  public Page<FluidIntake> getFluidBalanceInputTable(Long chartId, Pageable pageable){

    return inputTableRepository.findAll(pageable);
  }
}
