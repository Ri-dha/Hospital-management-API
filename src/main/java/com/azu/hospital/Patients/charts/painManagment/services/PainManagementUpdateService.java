package com.azu.hospital.Patients.charts.painManagment.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.painManagment.entity.PainManagementChart;
import com.azu.hospital.Patients.charts.painManagment.dao.PainManagementDao;
import com.azu.hospital.Patients.charts.painManagment.request.PainManagementRequest;
import com.azu.hospital.Patients.charts.painManagment.utils.PainManagementRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("PainManagementUpdateService")
public class PainManagementUpdateService {
  private final PainManagementDao chartDao;
  private final PatientDao patientDao;
  private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public PainManagementUpdateService(
          @Qualifier("PainManagementJpaDataAccess") PainManagementDao chartDao,
          @Qualifier("patientRepo") PatientDao patientDao,
          @Qualifier("exceptionsMessageReturn") ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
    this.patientDao = patientDao;
      this.messageReturn = messageReturn;
  }

  public IdResponse updateExistsChart(Long patientId, Long chartId, PainManagementRequest request){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Patient patient = patientDao.getPatientById(patientId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("patientNotFound")+" "+patientId
                    )
            );

    PainManagementChart existsChart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("PainManagementChartNotFound")+" "+chartId
                    )
            );

    PainManagementChart newChart = PainManagementRequestToChart.requestToChart(request);

    compareMethodExistChartAndUpdateChart(existsChart, newChart);

    newChart.setPatient(patient);

    PainManagementChart update = chartDao.updateExistsChart(newChart);

    return new DtoForReturnIdOnly(update.getId());
  }

  private void compareMethodExistChartAndUpdateChart(
          PainManagementChart existsChart,
          PainManagementChart newChart
  ){
    if(newChart.getPainFeel() == null){
      newChart.setPainFeel(existsChart.getPainFeel());
    }
    if(newChart.getPainFeelRadiating() == null){
      newChart.setPainFeelRadiating(existsChart.getPainFeelRadiating());
    }
    if(newChart.getPainGoAnywhereElse() == null){
      newChart.setPainGoAnywhereElse(existsChart.getPainGoAnywhereElse());
    }
    if(newChart.getMajorLifeChanges() == null){
      newChart.setMajorLifeChanges(existsChart.getMajorLifeChanges());
    }
    if(newChart.getPainStatus() == null){
      newChart.setPainStatus(existsChart.getPainStatus());
    }
    if(newChart.getPainStarted() == null){
      newChart.setPainStarted(existsChart.getPainStarted());
    }
    if(newChart.getPainStartDetails() == null){
      newChart.setPainStartDetails(existsChart.getPainStartDetails());
    }
    if(newChart.getPainWorse() == null){
      newChart.setPainWorse(existsChart.getPainWorse());
    }
    if(newChart.getPainWorseOther() == null){
      newChart.setPainWorseOther(existsChart.getPainWorseOther());
    }
    if(newChart.getPainBetter() == null){
      newChart.setPainBetter(existsChart.getPainBetter());
    }
    if(newChart.getPainBetterOther() == null){
      newChart.setPainBetterOther(existsChart.getPainBetterOther());
    }
    if(newChart.getTimePainGetWorse() == null){
      newChart.setTimePainGetWorse(existsChart.getTimePainGetWorse());
    }
    if(newChart.getTimePainGetWorseOther() == null){
      newChart.setTimePainGetWorseOther(existsChart.getTimePainGetWorseOther());
    }
    if(newChart.getPainDescribes() == null){
      newChart.setPainDescribes(existsChart.getPainDescribes());
    }
    if(newChart.getPainInterruptSleep() == null){
      newChart.setPainInterruptSleep(existsChart.getPainInterruptSleep());
    }
    if(newChart.getTriedTreatments() == null){
      newChart.setTriedTreatments(existsChart.getTriedTreatments());
    }
    if(newChart.getLastYearTest() == null){
      newChart.setLastYearTest(existsChart.getLastYearTest());
    }
    if(newChart.getOtherLastYearTest() == null){
      newChart.setOtherLastYearTest(existsChart.getOtherLastYearTest());
    }
  }

}
