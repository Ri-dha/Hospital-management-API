package com.azu.hospital.Patients.charts.burnDiagram.services;

import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnBodyDiagram;
import com.azu.hospital.Patients.charts.burnDiagram.request.BurnDiagramRequest;
import com.azu.hospital.Patients.charts.burnDiagram.utils.BurnDiagramRequestToChart;
import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnDiagramChart;
import com.azu.hospital.Patients.charts.burnDiagram.dao.BurnDiagramDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service("BurnDiagramUpdateService")
public class BurnDiagramUpdateService {
  private final BurnDiagramDao chartDao;
  private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public BurnDiagramUpdateService(BurnDiagramDao chartDao, ExceptionsMessageReturn messageReturn) {
    this.chartDao = chartDao;
      this.messageReturn = messageReturn;
  }

  public Map<String, Object> updateExistsChart(Long chartId, BurnDiagramRequest request){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Map<String, Object> response = new HashMap<>();

    BurnDiagramChart existsChart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("BurnDiagramChartNotFound")+" "+chartId
                    )
            );

    BurnDiagramChart newChart = BurnDiagramRequestToChart.requestToChart(request);

    compareMethodExistChartAndUpdateCart(existsChart, newChart);

    BurnDiagramChart update = chartDao.updateExistsChart(newChart);

    response.put("id", update.getId());

    return response;
  }
  private void compareMethodExistChartAndUpdateCart(
          BurnDiagramChart existsChart,
          BurnDiagramChart newChart
  ){

    if(newChart.getDateOfBurn() == null){
      newChart.setDateOfBurn(existsChart.getDateOfBurn());
    }
    if(newChart.getDateOfAdmission() == null){
      newChart.setDateOfAdmission(existsChart.getDateOfAdmission());
    }
    if(newChart.getDateOfTbsaEstimationPreliminary() == null){
      newChart.setDateOfTbsaEstimationPreliminary(existsChart.getDateOfTbsaEstimationPreliminary());
    }
    if(newChart.getPreliminaryDoctorName() == null){
      newChart.setPreliminaryDoctorName(existsChart.getPreliminaryDoctorName());
    }
    if(newChart.getDateOfTbsaEstimationFinal() == null){
      newChart.setDateOfTbsaEstimationFinal(existsChart.getDateOfTbsaEstimationFinal());
    }
    if(newChart.getFinalDoctorName() == null){
      newChart.setFinalDoctorName(existsChart.getFinalDoctorName());
    }
    if(newChart.getSuperficialDermal() == null){
      newChart.setSuperficialDermal(existsChart.getSuperficialDermal());
    }
    if(newChart.getMidDermal() == null){
      newChart.setMidDermal(existsChart.getMidDermal());
    }
    if(newChart.getDeepDermal() == null){
      newChart.setDeepDermal(existsChart.getDeepDermal());
    }
    if(newChart.getMidDermalFluidLoos() == null){
      newChart.setMidDermalFluidLoos(existsChart.getMidDermalFluidLoos());
    }

    updateBurnBodyDiagramMethod(existsChart, newChart);
  }
  private void updateBurnBodyDiagramMethod(
          BurnDiagramChart existsChart,
          BurnDiagramChart newChart
  ){
    if(newChart.getBurnBodyDiagram() == null){
      newChart.setBurnBodyDiagram(existsChart.getBurnBodyDiagram());
    }else{
      BurnBodyDiagram existsBodyDiagram = existsChart.getBurnBodyDiagram();
      BurnBodyDiagram newBodyDiagram = newChart.getBurnBodyDiagram();

      if(newBodyDiagram.getFrontHead() == null){
        newBodyDiagram.setFrontHead(existsBodyDiagram.getFrontHead());
      }
      if (newBodyDiagram.getBackHead() == null) {
        newBodyDiagram.setBackHead(existsBodyDiagram.getBackHead());
      }

      if (newBodyDiagram.getFrontNeck() == null) {
        newBodyDiagram.setFrontNeck(existsBodyDiagram.getFrontNeck());
      }

      if (newBodyDiagram.getBackNeck() == null) {
        newBodyDiagram.setBackNeck(existsBodyDiagram.getBackNeck());
      }

      if (newBodyDiagram.getFrontShould() == null) {
        newBodyDiagram.setFrontShould(existsBodyDiagram.getFrontShould());
      }

      if (newBodyDiagram.getBackShould() == null) {
        newBodyDiagram.setBackShould(existsBodyDiagram.getBackShould());
      }

      if (newBodyDiagram.getLeftFrontUpperArm() == null) {
        newBodyDiagram.setLeftFrontUpperArm(existsBodyDiagram.getLeftFrontUpperArm());
      }

      if (newBodyDiagram.getLeftBackUpperArm() == null) {
        newBodyDiagram.setLeftBackUpperArm(existsBodyDiagram.getLeftBackUpperArm());
      }

      if (newBodyDiagram.getLeftFrontForearm() == null) {
        newBodyDiagram.setLeftFrontForearm(existsBodyDiagram.getLeftFrontForearm());
      }

      if (newBodyDiagram.getLeftBackForearm() == null) {
        newBodyDiagram.setLeftBackForearm(existsBodyDiagram.getLeftBackForearm());
      }

      if (newBodyDiagram.getLeftFrontHand() == null) {
        newBodyDiagram.setLeftFrontHand(existsBodyDiagram.getLeftFrontHand());
      }

      if (newBodyDiagram.getLeftBackHand() == null) {
        newBodyDiagram.setLeftBackHand(existsBodyDiagram.getLeftBackHand());
      }

      if (newBodyDiagram.getRightFrontUpperArm() == null) {
        newBodyDiagram.setRightFrontUpperArm(existsBodyDiagram.getRightFrontUpperArm());
      }

      if (newBodyDiagram.getRightBackUpperArm() == null) {
        newBodyDiagram.setRightBackUpperArm(existsBodyDiagram.getRightBackUpperArm());
      }

      if (newBodyDiagram.getRightFrontForearm() == null) {
        newBodyDiagram.setRightFrontForearm(existsBodyDiagram.getRightFrontForearm());
      }

      if (newBodyDiagram.getRightBackForearm() == null) {
        newBodyDiagram.setRightBackForearm(existsBodyDiagram.getRightBackForearm());
      }

      if (newBodyDiagram.getRightFrontHand() == null) {
        newBodyDiagram.setRightFrontHand(existsBodyDiagram.getRightFrontHand());
      }

      if (newBodyDiagram.getRightBackHand() == null) {
        newBodyDiagram.setRightBackHand(existsBodyDiagram.getRightBackHand());
      }

      if (newBodyDiagram.getChest() == null) {
        newBodyDiagram.setChest(existsBodyDiagram.getChest());
      }

      if (newBodyDiagram.getAbdomen() == null) {
        newBodyDiagram.setAbdomen(existsBodyDiagram.getAbdomen());
      }

      if (newBodyDiagram.getLeftShoulderBlade() == null) {
        newBodyDiagram.setLeftShoulderBlade(existsBodyDiagram.getLeftShoulderBlade());
      }

      if (newBodyDiagram.getRightShoulderBlade() == null) {
        newBodyDiagram.setRightShoulderBlade(existsBodyDiagram.getRightShoulderBlade());
      }

      if (newBodyDiagram.getLeftBack() == null) {
        newBodyDiagram.setLeftBack(existsBodyDiagram.getLeftBack());
      }

      if (newBodyDiagram.getRightBack() == null) {
        newBodyDiagram.setRightBack(existsBodyDiagram.getRightBack());
      }

      if (newBodyDiagram.getLeftLumbarRegion() == null) {
        newBodyDiagram.setLeftLumbarRegion(existsBodyDiagram.getLeftLumbarRegion());
      }

      if (newBodyDiagram.getRightLumbarRegion() == null) {
        newBodyDiagram.setRightLumbarRegion(existsBodyDiagram.getRightLumbarRegion());
      }

      if (newBodyDiagram.getRightButtock() == null) {
        newBodyDiagram.setRightButtock(existsBodyDiagram.getRightButtock());
      }

      if (newBodyDiagram.getLeftButtock() == null) {
        newBodyDiagram.setLeftButtock(existsBodyDiagram.getLeftButtock());
      }

      if (newBodyDiagram.getSexualOrgan() == null) {
        newBodyDiagram.setSexualOrgan(existsBodyDiagram.getSexualOrgan());
      }

      if (newBodyDiagram.getFrontLeftThigh() == null) {
        newBodyDiagram.setFrontLeftThigh(existsBodyDiagram.getFrontLeftThigh());
      }

      if (newBodyDiagram.getBackLeftThigh() == null) {
        newBodyDiagram.setBackLeftThigh(existsBodyDiagram.getBackLeftThigh());
      }

      if (newBodyDiagram.getFrontBackThigh() == null) {
        newBodyDiagram.setFrontBackThigh(existsBodyDiagram.getFrontBackThigh());
      }

      if (newBodyDiagram.getBackBackThigh() == null) {
        newBodyDiagram.setBackBackThigh(existsBodyDiagram.getBackBackThigh());
      }

      if (newBodyDiagram.getFrontLeftLeg() == null) {
        newBodyDiagram.setFrontLeftLeg(existsBodyDiagram.getFrontLeftLeg());
      }

      if (newBodyDiagram.getBackLeftLeg() == null) {
        newBodyDiagram.setBackLeftLeg(existsBodyDiagram.getBackLeftLeg());
      }

      if (newBodyDiagram.getFrontBackLeg() == null) {
        newBodyDiagram.setFrontBackLeg(existsBodyDiagram.getFrontBackLeg());
      }

      if (newBodyDiagram.getBackBackLeg() == null) {
        newBodyDiagram.setBackBackLeg(existsBodyDiagram.getBackBackLeg());
      }

      if (newBodyDiagram.getFrontLeftFeet() == null) {
        newBodyDiagram.setFrontLeftFeet(existsBodyDiagram.getFrontLeftFeet());
      }

      if (newBodyDiagram.getBackLeftFeet() == null) {
        newBodyDiagram.setBackLeftFeet(existsBodyDiagram.getBackLeftFeet());
      }

      if (newBodyDiagram.getFrontBackFeet() == null) {
        newBodyDiagram.setFrontBackFeet(existsBodyDiagram.getFrontBackFeet());
      }

      if (newBodyDiagram.getBackBackFeet() == null) {
        newBodyDiagram.setBackBackFeet(existsBodyDiagram.getBackBackFeet());
      }


    }

  }
}
