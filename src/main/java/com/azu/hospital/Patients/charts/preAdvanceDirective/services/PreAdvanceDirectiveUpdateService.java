package com.azu.hospital.Patients.charts.preAdvanceDirective.services;

import com.azu.hospital.Patients.charts.preAdvanceDirective.entity.PreAdvanceDirectiveChart;
import com.azu.hospital.Patients.charts.preAdvanceDirective.dao.PreAdvanceDirectiveDao;
import com.azu.hospital.Patients.charts.preAdvanceDirective.request.PreAdvanceDirectiveRequest;
import com.azu.hospital.Patients.charts.preAdvanceDirective.utils.PreAdvanceDirectiveRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("PreAdvanceDirectiveUpdateService")
public class PreAdvanceDirectiveUpdateService {

  private final PreAdvanceDirectiveDao chartDao;
  private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public PreAdvanceDirectiveUpdateService(PreAdvanceDirectiveDao chartDao, ExceptionsMessageReturn messageReturn) {
    this.chartDao = chartDao;
      this.messageReturn = messageReturn;
  }

  public IdResponse updateExistsChart(Long chartId, PreAdvanceDirectiveRequest request){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    PreAdvanceDirectiveChart existsChart = chartDao.findChartById(chartId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("PreAdvanceDirectiveChartNotFound")+ " " + chartId
                            )
                    );

    PreAdvanceDirectiveChart newChart = PreAdvanceDirectiveRequestToChart.requestToChart(request);

    compareMethodExistChartAndUpdateCart(existsChart, newChart);

    newChart.setPatient(existsChart.getPatient());

    PreAdvanceDirectiveChart savedChart = chartDao.updateExistsChart(newChart);

    return new DtoForReturnIdOnly(savedChart.getId());
  }
  private void compareMethodExistChartAndUpdateCart(
          PreAdvanceDirectiveChart existsChart,
          PreAdvanceDirectiveChart newChart
  ){
    if (newChart.getOption() == null){
      newChart.setOption(existsChart.getOption());
    }
    if (newChart.getPrintName() == null){
      newChart.setPrintName(existsChart.getPrintName());
    }
    if (newChart.getSignature() == null){
      newChart.setSignature(existsChart.getSignature());
    }
    if (newChart.getDate() == null){
      newChart.setDate(existsChart.getDate());
    }
    if (newChart.getPreOpNurseSignature() == null){
      newChart.setPreOpNurseSignature(existsChart.getPreOpNurseSignature());
    }
    if (newChart.getPreOpNurseDate() == null){
      newChart.setPreOpNurseDate(existsChart.getPreOpNurseDate());
    }
    if (newChart.getPreOpNurseTime() == null){
      newChart.setPreOpNurseTime(existsChart.getPreOpNurseTime());
    }
  }
}
