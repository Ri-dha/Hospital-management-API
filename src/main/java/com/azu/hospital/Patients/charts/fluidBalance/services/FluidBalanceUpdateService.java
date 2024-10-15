package com.azu.hospital.Patients.charts.fluidBalance.services;

import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidBalanceChart;
import com.azu.hospital.Patients.charts.fluidBalance.dao.FluidBalanceDao;
import com.azu.hospital.Patients.charts.fluidBalance.request.FluidBalanceRequest;
import com.azu.hospital.Patients.charts.fluidBalance.utils.FluidBalanceRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service("FluidBalanceUpdateService")
public class FluidBalanceUpdateService {
  private final FluidBalanceDao chartDao;
  private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public FluidBalanceUpdateService(
          @Qualifier("FluidBalanceJpaDataAccess")
          FluidBalanceDao chartDao, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
      this.messageReturn = messageReturn;
  }

  public Map<String, Object> updateExistsChart(Long chartId, FluidBalanceRequest request){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Map<String, Object> response = new HashMap<>();

    FluidBalanceChart existsChart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("FluidBalanceChartNotFound")+" "+chartId
                    )
            );

    FluidBalanceChart newChart = FluidBalanceRequestToChart.requestToChart(request);

    compareAndUpdateMethod(existsChart, newChart);

    FluidBalanceChart updated = chartDao.updateExistsChart(newChart);

    response.put("id", updated.getId());

    return response;
  }

  private void compareAndUpdateMethod(
          FluidBalanceChart existsChart,
          FluidBalanceChart newChart
  ){
    if(newChart.getDateFrom() == null){
      newChart.setDateFrom(existsChart.getDateFrom());
    }
    if(newChart.getDateTo() == null){
      newChart.setDateTo(existsChart.getDateTo());
    }
    if(newChart.getMl24InputHour() == null){
      newChart.setMl24InputHour(existsChart.getMl24InputHour());
    }
    if(newChart.getInputs() == null){
      newChart.setInputs(existsChart.getInputs());
    }
    if(newChart.getFluidBalance24InputHour() == null){
      newChart.setFluidBalance24InputHour(existsChart.getFluidBalance24InputHour());
    }
    if(newChart.getCandidate() == null){
      newChart.setCandidate(existsChart.getCandidate());
    }
  }

}
