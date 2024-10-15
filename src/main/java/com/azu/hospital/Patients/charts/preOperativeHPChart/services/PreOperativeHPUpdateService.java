package com.azu.hospital.Patients.charts.preOperativeHPChart.services;

import com.azu.hospital.Patients.charts.preOperativeHPChart.entity.PreOperativeHPChart;
import com.azu.hospital.Patients.charts.preOperativeHPChart.dao.PreOperativeHPChartDao;
import com.azu.hospital.Patients.charts.preOperativeHPChart.request.PreOperativeHPChartRequest;
import com.azu.hospital.Patients.charts.preOperativeHPChart.utils.PreOperativeHPRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service("PreOperativeHPUpdateService")
public class PreOperativeHPUpdateService {

  private final PreOperativeHPChartDao chartDao;
  private final ExceptionsMessageReturn messageReturn;


  @Autowired
  public PreOperativeHPUpdateService(
          @Qualifier("PreOperativeHpChartJpaDataAccess")
          PreOperativeHPChartDao chartDao, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
      this.messageReturn = messageReturn;
  }

  public Map<String, Object> updateExistsChart(Long chartId, PreOperativeHPChartRequest request) {
    Map<String, Object> response = new HashMap<>();
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    PreOperativeHPChart newChart = PreOperativeHPRequestToChart.requestToChart(request);
    PreOperativeHPChart existsChart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("PreOperativeHPChartNotFound") + " " + chartId
                    )
            );

    compareMethodExistChartAndUpdateCart(existsChart, newChart);

    newChart.setPatient(existsChart.getPatient());

    PreOperativeHPChart update = chartDao.updateExistsChart(newChart);

    response.put("id", update.getId());

    return response;
  }

  private static void compareMethodExistChartAndUpdateCart(
          PreOperativeHPChart existsChart,
          PreOperativeHPChart newChart
  ) {
    if (newChart.getDiagnosisChiefComplaint() == null) {
      newChart.setDiagnosisChiefComplaint(existsChart.getDiagnosisChiefComplaint());
    }
    if (newChart.getPastMedicalHistory() == null) {
      newChart.setPastMedicalHistory(existsChart.getPastMedicalHistory());
    }
    if (newChart.getFamilyPhysician() == null) {
      newChart.setFamilyPhysician(existsChart.getFamilyPhysician());
    }
    if (newChart.getSurgicalHistory() == null) {
      newChart.setSurgicalHistory(existsChart.getSurgicalHistory());
    }
    if (newChart.getMedications() == null) {
      newChart.setMedications(existsChart.getMedications());
    }
    if (newChart.getHabit() == null) {
      newChart.setHabit(existsChart.getHabit());
    }
    if (newChart.getChronicDisease() == null) {
      newChart.setChronicDisease(existsChart.getChronicDisease());
    }
    if (newChart.getFamilyHistory() == null) {
      newChart.setFamilyHistory(existsChart.getFamilyHistory());
    }
    if (newChart.getFamilyHistory() == null) {
      newChart.setFamilyHistory(existsChart.getFamilyHistory());
    }

    if (newChart.getGeneral() == null) {
      newChart.setGeneral(existsChart.getGeneral());
    }
    if (newChart.getHeadNeck() == null) {
      newChart.setHeadNeck(existsChart.getHeadNeck());
    }
    if (newChart.getChest() == null) {
      newChart.setChest(existsChart.getChest());
    }
    if (newChart.getHeart() == null) {
      newChart.setHeart(existsChart.getHeart());
    }
    if (newChart.getLungs() == null) {
      newChart.setLungs(existsChart.getLungs());
    }
    if (newChart.getAbdomen() == null) {
      newChart.setAbdomen(existsChart.getAbdomen());
    }
    if (newChart.getSkinOfExtremities() == null) {
      newChart.setSkinOfExtremities(existsChart.getSkinOfExtremities());
    }
    if (newChart.getImpression() == null) {
      newChart.setImpression(existsChart.getImpression());
    }
  }
}
