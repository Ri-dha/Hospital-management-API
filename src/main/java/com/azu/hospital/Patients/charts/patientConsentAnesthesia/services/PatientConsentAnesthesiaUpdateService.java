package com.azu.hospital.Patients.charts.patientConsentAnesthesia.services;

import com.azu.hospital.Patients.charts.patientConsentAnesthesia.request.PatientConsentAnesthesiaRequest;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.utils.PatientConsentAnesthesiaRequestToChart;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.entity.PatientConsentAnesthesiaChart;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.dao.PatientConsentAnesthesiaDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service("PatientConsentAnesthesiaUpdateService")
public class PatientConsentAnesthesiaUpdateService {
  private final ExceptionsMessageReturn messageReturn;

  private final PatientConsentAnesthesiaDao chartDao;

  public PatientConsentAnesthesiaUpdateService(
          ExceptionsMessageReturn messageReturn, @Qualifier("PatientConsentAnesthesiaJpaDataAccess")
          PatientConsentAnesthesiaDao chartDao
  ) {
      this.messageReturn = messageReturn;
      this.chartDao = chartDao;
  }

  public Map<String, Object> updateExistsChart(Long chartId, PatientConsentAnesthesiaRequest request){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Map<String, Object> response = new HashMap<>();

    PatientConsentAnesthesiaChart newChart = PatientConsentAnesthesiaRequestToChart.requestToChart(request);

    PatientConsentAnesthesiaChart existsChart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("PatientConsentAnesthesiaChartNotFound") + " " + chartId
                    )
            );

    compareMethodExistChartAndUpdateCart(existsChart, newChart);

    newChart.setPatient(existsChart.getPatient());

    PatientConsentAnesthesiaChart savedChart = chartDao.updateExistsChart(newChart);

    response.put("id", savedChart.getId());

    return response;
  }
  private void compareMethodExistChartAndUpdateCart(
          PatientConsentAnesthesiaChart existsChart,
          PatientConsentAnesthesiaChart newChart
  ){
    if(newChart.getAnesthesiaType() == null){
      newChart.setAnesthesiaType(existsChart.getAnesthesiaType());
    }
    if(newChart.getPatientSignature() == null){
      newChart.setPatientSignature(existsChart.getPatientSignature());
    }
    if(newChart.getPatientSignatureDate() == null){
      newChart.setPatientSignatureDate(existsChart.getPatientSignatureDate());
    }
    if(newChart.getPatientSignatureTime() == null){
      newChart.setPatientSignatureTime(existsChart.getPatientSignatureTime());
    }
    if(newChart.getWitnessSignature() == null){
      newChart.setWitnessSignature(existsChart.getWitnessSignature());
    }
    if(newChart.getWitnessSignatureDate() == null){
      newChart.setWitnessSignatureDate(existsChart.getWitnessSignatureDate());
    }
    if(newChart.getWitnessSignatureTime() == null){
      newChart.setPatientSignatureTime(existsChart.getWitnessSignatureTime());
    }
    if(newChart.getAnesthesiaProviderSignature() == null){
      newChart.setAnesthesiaProviderSignature(existsChart.getAnesthesiaProviderSignature());
    }
    if(newChart.getAnesthesiaProviderSignatureDate() == null){
      newChart.setAnesthesiaProviderSignatureDate(existsChart.getAnesthesiaProviderSignatureDate());
    }
    if(newChart.getAnesthesiaProviderSignatureTime() == null){
      newChart.setAnesthesiaProviderSignatureTime(existsChart.getAnesthesiaProviderSignatureTime());
    }
  }
}
