package com.azu.hospital.Patients.charts.fluidBalance.services;

import com.azu.hospital.Patients.charts.fluidBalance.dto.FluidIntakeDto;
import com.azu.hospital.Patients.charts.fluidBalance.dto.FluidIntakeDtoMapper;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidBalanceChart;
import com.azu.hospital.Patients.charts.fluidBalance.dao.FluidBalanceDao;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidIntake;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FieldFluidRequest;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FluidByIntravenous;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.Oral;
import com.azu.hospital.Patients.charts.fluidBalance.request.FluidIntakeRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("FluidBalanceInputTableService")
public class FluidIntakeService {
  private final FluidBalanceDao chartDao;
  private final FluidIntakeDtoMapper dtoMapper;
  private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public FluidIntakeService(
          @Qualifier("FluidBalanceJpaDataAccess")
          FluidBalanceDao chartDao,
          FluidIntakeDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
    this.dtoMapper = dtoMapper;
      this.messageReturn = messageReturn;
  }

  public List<FluidIntakeDto> getFluidBalanceInputTable(
          Long chartId
  ){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    FluidBalanceChart chart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("FluidBalanceChartNotFound")+" "+chartId
                    )
            );

    List<FluidIntakeDto> response = new ArrayList<>();

    for (FluidIntake input: chart.getInputs()){
      response.add(dtoMapper.chartToDto(input));
    }
    return response;
  }

  public Map<String, Object> addNewFluidInput(
          Long chartId,
          FluidIntakeRequest request
  ) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Map<String, Object> response = new HashMap<>();

    FluidBalanceChart chart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("FluidBalanceChartNotFound")+" "+chartId
                    )
            );


    List<FluidIntake> inputs = new ArrayList<>(chart.getInputs());

    FluidIntake fluidIntake = new FluidIntake(
            request.time(),
            request.oral(),
            request.fluidByIntravenous(),
            request.hourlyTotal(),
            request.runningInputTotal(),
            request.nasogastricFeed()
    );

    inputs.add(fluidIntake);

    chart.setInputs(inputs);

    FluidIntake updatedInput = chartDao.createNewInputRow(fluidIntake);

    response.put("id", updatedInput.getFluidIntakeId());

    return response;
  }

  public Map<String, Object> updateFluidInput(
          Long rowId,
          Long chartId,
          FluidIntakeRequest request
  ){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Map<String, Object> response = new HashMap<>();

    FluidBalanceChart chart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("FluidBalanceChartNotFound0")+" "+chartId
                    )
            );

    FluidIntake existsRow = chartDao.getInputRowById(rowId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("FluidIntakeNotFound")+" "+rowId
                    )
            );

    FluidIntake newRow = new FluidIntake(
            request.time(),
            request.oral(),
            request.fluidByIntravenous(),
            request.hourlyTotal(),
            request.runningInputTotal(),
            request.nasogastricFeed()
    );

    chart.getInputs().add(newRow);

    compareAndUpdateMethod(existsRow, newRow);

    FluidIntake updated = chartDao.updateExistsInputRow(newRow);

    response.put("id", updated.getFluidIntakeId());

    return response;
  }

  private static void compareAndUpdateMethod(FluidIntake existsRow, FluidIntake newRow) {
    if (newRow.getTime() == null){
      newRow.setTime(existsRow.getTime());
    }

    if (newRow.getOral() == null){
      newRow.setOral(existsRow.getOral());
    }else {
      Oral newOral = newRow.getOral();
      Oral existsOral = existsRow.getOral();
      if (newOral.getTotal() == null){
        newOral.setTotal(existsOral.getTotal());
      }
      if (newOral.getVolume() == null){
        newOral.setVolume(existsOral.getVolume());
      }
      if (newOral.getType() == null){
        newOral.setType(existsOral.getType());
      }
    }

    if (newRow.getNasogastricFeed() == null){
      newRow.setNasogastricFeed(existsRow.getNasogastricFeed());
    }else {
      FieldFluidRequest newNasogastricFeed = newRow.getNasogastricFeed();
      FieldFluidRequest existsNasogastricFeed = existsRow.getNasogastricFeed();
      if (newNasogastricFeed.getTotal() == null){
        newNasogastricFeed.setTotal(existsNasogastricFeed.getTotal());
      }
      if (newNasogastricFeed.getValue() == null){
        newNasogastricFeed.setValue(existsNasogastricFeed.getValue());
      }
    }

    if (newRow.getFluidByIntravenous() == null){
      newRow.setFluidByIntravenous(existsRow.getFluidByIntravenous());
    }else {
      FluidByIntravenous newFluidByIntravenous = newRow.getFluidByIntravenous();
      FluidByIntravenous existsFluidByIntravenous = existsRow.getFluidByIntravenous();
      if (newFluidByIntravenous.getFirstFluidIntake() == null){
        newFluidByIntravenous.setFirstFluidIntake(existsFluidByIntravenous.getFirstFluidIntake());
      }
      if (newFluidByIntravenous.getSecondFluidIntake() == null){
        newFluidByIntravenous.setSecondFluidIntake(existsFluidByIntravenous.getSecondFluidIntake());
      }
      if (newFluidByIntravenous.getThirdFluidIntake() == null){
        newFluidByIntravenous.setThirdFluidIntake(existsFluidByIntravenous.getThirdFluidIntake());
      }
      if (newFluidByIntravenous.getFlush() == null){
        newFluidByIntravenous.setFlush(existsFluidByIntravenous.getFlush());
      }
      if (newFluidByIntravenous.getFirstFluidIntakeTotal() == null){
        newFluidByIntravenous.setFirstFluidIntakeTotal(existsFluidByIntravenous.getFirstFluidIntakeTotal());
      }
      if (newFluidByIntravenous.getSecondFluidIntakeTotal() == null){
        newFluidByIntravenous.setSecondFluidIntakeTotal(existsFluidByIntravenous.getSecondFluidIntakeTotal());
      }
      if (newFluidByIntravenous.getThirdFluidIntakeTotal() == null){
        newFluidByIntravenous.setThirdFluidIntakeTotal(existsFluidByIntravenous.getThirdFluidIntakeTotal());
      }
      if (newFluidByIntravenous.getFlushTotal() == null){
        newFluidByIntravenous.setFlushTotal(existsFluidByIntravenous.getFlushTotal());
      }
    }

    if (newRow.getHourlyTotal() == null){
      newRow.setHourlyTotal(existsRow.getHourlyTotal());
    }else {
      FieldFluidRequest newHourlyTotal = newRow.getHourlyTotal();
      FieldFluidRequest existsHourlyTotal = existsRow.getHourlyTotal();
      if (newHourlyTotal.getTotal() == null){
        newHourlyTotal.setTotal(existsHourlyTotal.getTotal());
      }
      if (newHourlyTotal.getValue() == null){
        newHourlyTotal.setValue(existsHourlyTotal.getValue());
      }
    }

    if (newRow.getRunningInputTotal() == null){
      newRow.setRunningInputTotal(existsRow.getRunningInputTotal());
    }else {
      FieldFluidRequest newHRunningInputTotal = newRow.getRunningInputTotal();
      FieldFluidRequest existsHRunningInputTotal = existsRow.getRunningInputTotal();
      if (newHRunningInputTotal.getTotal() == null){
        newHRunningInputTotal.setTotal(existsHRunningInputTotal.getTotal());
      }
      if (newHRunningInputTotal.getValue() == null){
        newHRunningInputTotal.setValue(existsHRunningInputTotal.getValue());
      }
    }
  }
}
