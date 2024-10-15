package com.azu.hospital.Patients.charts.burnDiagram.utils;


import com.azu.hospital.Patients.charts.burnDiagram.request.BurnDiagramRequest;
import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnDiagramChart;

public class BurnDiagramRequestToChart {
  public static BurnDiagramChart requestToChart(BurnDiagramRequest request){
    return new BurnDiagramChart.Builder()
            .withDateOfBurn(String.valueOf(request.dateOfBurn()))
            .withDateOfAdmission(request.dateOfAdmission())
            .withDateOfTbsaEstimationPreliminary(request.dateOfTbsaEstimationPreliminary())
            .withPreliminaryDoctorName(request.preliminaryDoctorName())
            .withDateOfTbsaEstimationFinal(request.dateOfTbsaEstimationFinal())
            .withFinalDoctorName(request.finalDoctorName())
            .withSuperficialDermal(request.superficialDermal())
            .withMidDermal(request.midDermal())
            .withDeepDermal(request.deepDermal())
            .withMidDermalFluidLoos(request.midDermalFluidLoos())
            .withDeepDermalFluidLoss(request.deepDermalFluidLoss())
            .withFinalTbsa(request.finalTbsa())
            .withTotalFluidLoos(request.totalFluidLoos())
            .withBurnBodyDiagram(request.burnBodyDiagram())
            .build();
  }
}
