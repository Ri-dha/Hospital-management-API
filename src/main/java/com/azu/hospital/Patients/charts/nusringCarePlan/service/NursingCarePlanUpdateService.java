package com.azu.hospital.Patients.charts.nusringCarePlan.service;

import com.azu.hospital.Patients.charts.nusringCarePlan.dao.NursingCarePlanDao;
import com.azu.hospital.Patients.charts.nusringCarePlan.entity.NursingCarePlan;
import com.azu.hospital.Patients.charts.nusringCarePlan.request.NursingCarePlanRequest;
import com.azu.hospital.Patients.charts.nusringCarePlan.utils.NursingCarePlanRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class NursingCarePlanUpdateService {
  private final NursingCarePlanDao nursingCarePlanDao;
  private final ExceptionsMessageReturn messageReturn;

  public NursingCarePlanUpdateService(NursingCarePlanDao nursingCarePlanDao, ExceptionsMessageReturn messageReturn) {
    this.nursingCarePlanDao = nursingCarePlanDao;
      this.messageReturn = messageReturn;
  }

  public IdResponse updateNursingCareChart(
          NursingCarePlanRequest request,
          Long chartId
  ){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    NursingCarePlan existsChart = nursingCarePlanDao
            .findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("NursingCarePlanChartNotFound")+" "+chartId

                    )
            );

        NursingCarePlan newChart = NursingCarePlanRequestToChart
                .getNursingCarePlan(request);
    compareMethodExistChartAndUpdateCart(existsChart,newChart);
    newChart.setPatient(existsChart.getPatient());

    NursingCarePlan savedChart = nursingCarePlanDao.updateExistChart(newChart);

  return new DtoForReturnIdOnly(savedChart.getId());

  }
  private static void compareMethodExistChartAndUpdateCart(
          NursingCarePlan existsChart,
          NursingCarePlan newChart)
  {
    if (newChart.getNursingDiagnosis() == null){
      newChart.setNursingDiagnosis(existsChart.getNursingDiagnosis());
    }
    if (newChart.getGoalsAndOutComes() == null){
      newChart.setGoalsAndOutComes(existsChart.getGoalsAndOutComes());
    }
    if (newChart.getIntervensions() == null){
      newChart.setIntervensions(existsChart.getIntervensions());
    }
    if (newChart.getEvaluation() == null){
      newChart.setEvaluation(existsChart.getEvaluation());
    }





  }


}
