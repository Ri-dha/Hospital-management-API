package com.azu.hospital.Patients.charts.neurologicalObservation.services;


import com.azu.hospital.Patients.charts.neurologicalObservation.entities.NeurologicalObservationChart;
import com.azu.hospital.Patients.charts.neurologicalObservation.dao.NeurologicalObservationDao;
import com.azu.hospital.Patients.charts.neurologicalObservation.entities.AdultEntity;
import com.azu.hospital.Patients.charts.neurologicalObservation.entities.ChildEntity;
import com.azu.hospital.Patients.charts.neurologicalObservation.request.NeurologicalObservationRequest;
import com.azu.hospital.Patients.charts.neurologicalObservation.utils.NeurologicalObservationRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service("NeurologicalObservationUpdateService")
public class NeurologicalObservationUpdateService {
  private final NeurologicalObservationDao chartDao;
  private final ExceptionsMessageReturn messageReturn;


  public NeurologicalObservationUpdateService(
          @Qualifier("NeurologicalObservationJpaDataAccess")
          NeurologicalObservationDao chartDao, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
      this.messageReturn = messageReturn;
  }

  public Map<String, Object> updateExistsChart(Long chartId, NeurologicalObservationRequest request){
    Map<String, Object> response = new HashMap<>();
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    NeurologicalObservationChart existsChart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("NeurologicalObservationChartNotFound")+" "+chartId
                    )
            );
    NeurologicalObservationChart newChart = NeurologicalObservationRequestToChart.requestToChart(request);

    compareMethodExistChartAndUpdateCart(existsChart, newChart);

    newChart.setPatient(existsChart.getPatient());

    NeurologicalObservationChart update = chartDao.updateExistsChart(newChart);

    response.put("id", update.getId());

    return response;
  }
  private void compareMethodExistChartAndUpdateCart(
          NeurologicalObservationChart existsChart,
          NeurologicalObservationChart newChart
  ){
    if(newChart.getDate() == null){
      newChart.setDate(existsChart.getDate());
    }
    if(newChart.getCandidateName() == null){
      newChart.setCandidateName(existsChart.getCandidateName());
    }

    checkAndUpdateAdultMethod(existsChart, newChart);
    checkAndUpdateChildMethod(existsChart, newChart);

    if(newChart.getNote() == null){
      newChart.setNote(existsChart.getNote());
    }
  }

  private void checkAndUpdateAdultMethod(
          NeurologicalObservationChart existsChart,
          NeurologicalObservationChart newChart
  ){
    if(newChart.getAdult() == null){
      newChart.setAdult(existsChart.getAdult());
    }else {
      AdultEntity existsAdult = existsChart.getAdult();
      AdultEntity newAdult = newChart.getAdult();

      if(newAdult.getEyeOpening() == null){
        newAdult.setEyeOpening(existsAdult.getEyeOpening());
      }
      if(newAdult.getVerbalResponse() == null){
        newAdult.setVerbalResponse(existsAdult.getVerbalResponse());
      }
      if(newAdult.getMotorResponse() == null){
        newAdult.setMotorResponse(existsAdult.getMotorResponse());
      }
      if(newAdult.getLeftPupil() == null){
        newAdult.setLeftPupil(existsAdult.getLeftPupil());
      }
      if(newAdult.getRightPupil() == null){
        newAdult.setRightPupil(existsAdult.getRightPupil());
      }
      if(newAdult.getLegs() == null){
        newAdult.setLegs(existsAdult.getLegs());
      }
      if(newAdult.getArms() == null){
        newAdult.setArms(existsAdult.getArms());
      }
    }
  }

  private void checkAndUpdateChildMethod(
          NeurologicalObservationChart existsChart,
          NeurologicalObservationChart newChart
  ){
    if(newChart.getChild() == null){
      newChart.setChild(existsChart.getChild());
    }else {
      ChildEntity existsChild = existsChart.getChild();
      ChildEntity newChild = newChart.getChild();

      if(newChild.getAppearance() == null){
        newChild.setAppearance(existsChild.getAppearance());
      }
      if(newChild.getPulse() == null){
        newChild.setPulse(existsChild.getPulse());
      }
      if(newChild.getGrimace() == null){
        newChild.setGrimace(existsChild.getGrimace());
      }
      if(newChild.getActivity() == null){
        newChild.setActivity(existsChild.getActivity());
      }
      if(newChild.getRespiration() == null){
        newChild.setRespiration(existsChild.getRespiration());
      }
    }
  }
}
