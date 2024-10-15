package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.services;

import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.request.AnesthesiaPhysicianOrdersRequest;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.utils.AnesthesiaPhysicianOrdersRequestToChart;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity.AnesthesiaPhysicianOrders;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dao.AnesthesiaPhysicianOrdersDao;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service("AnesthesiaPhysicianOrdersUpdateService")
public class AnesthesiaPhysicianOrdersUpdateService {

  private final AnesthesiaPhysicianOrdersDao chartDao;
  private final ExceptionsMessageReturn messageReturn;

  public AnesthesiaPhysicianOrdersUpdateService(
          @Qualifier("AnesthesiaPhysicianOrdersJpaDataAccess")
          AnesthesiaPhysicianOrdersDao chartDao, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
      this.messageReturn = messageReturn;
  }

  public Map<String, Object> updateExistsChart(Long chartId, AnesthesiaPhysicianOrdersRequest request){
    Map<String, Object> response = new HashMap<>();
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    AnesthesiaPhysicianOrders existsChart = chartDao.findChartId(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("AnesthesiaPhysicianOrdersChartNotFound")+" "+chartId
                    )
            );

    AnesthesiaPhysicianOrders newChart = AnesthesiaPhysicianOrdersRequestToChart.requestToChart(request);

    compareMethodExistChartAndUpdateCart(existsChart, newChart);
    newChart.setPatient(existsChart.getPatient());
    AnesthesiaPhysicianOrders update = chartDao.updateExistsChart(newChart);

    response.put("id", update.getId());

    return response;
  }

  private static void compareMethodExistChartAndUpdateCart(
          AnesthesiaPhysicianOrders existsChart,
          AnesthesiaPhysicianOrders newChart
  )
  {
    if(newChart.getAnesthesiologistName() == null){
    newChart.setAnesthesiologistName(existsChart.getAnesthesiologistName());
  }
    if(newChart.getOxygen() == null){
      newChart.setOxygen(existsChart.getOxygen());
    }
    if(newChart.getIvPerHour() == null){
      newChart.setIvPerHour(existsChart.getIvPerHour());
    }
    if(newChart.getIvRun() == null){
      newChart.setIvRun(existsChart.getIvRun());
    }
    if(newChart.getDcIv() == null){
      newChart.setDcIv(existsChart.getDcIv());
    }
    if(newChart.getToradol() == null){
      newChart.setToradol(existsChart.getToradol());
    }
    if(newChart.getToradolMg() == null){
      newChart.setToradolMg(existsChart.getToradolMg());
    }
    if(newChart.getOtherParenteral() == null){
      newChart.setOtherParenteral(existsChart.getOtherParenteral());
    }
    if(newChart.getMotrin() == null){
      newChart.setMotrin(existsChart.getMotrin());
    }
    if(newChart.getOtherOral() == null){
      newChart.setOtherOral(existsChart.getOtherOral());
    }
    if(newChart.getZofran4mg() == null){
      newChart.setZofran4mg(existsChart.getZofran4mg());
    }
    if(newChart.getOtherZofran() == null){
      newChart.setOtherZofran(existsChart.getOtherZofran());
    }
    if(newChart.getAdditionalOrders() == null){
      newChart.setAdditionalOrders(existsChart.getAdditionalOrders());
    }
  }

}
