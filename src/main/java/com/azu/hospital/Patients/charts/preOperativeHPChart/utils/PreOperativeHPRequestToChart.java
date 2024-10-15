package com.azu.hospital.Patients.charts.preOperativeHPChart.utils;

import com.azu.hospital.Patients.charts.preOperativeHPChart.entity.PreOperativeHPChart;
import com.azu.hospital.Patients.charts.preOperativeHPChart.request.PreOperativeHPChartRequest;

public class PreOperativeHPRequestToChart {
  public static PreOperativeHPChart requestToChart(PreOperativeHPChartRequest request) {
    return new PreOperativeHPChart.Builder()
            .withDiagnosisChiefComplaint(request.diagnosisChiefComplaint())
            .withPastMedicalHistory(request.pastMedicalHistory())
            .withFamilyPhysician(request.familyPhysician())
            .withSurgicalHistory(request.surgicalHistory())
            .withMedications(request.medications())
            .withHabit(request.hobbies())
            .withChronicDisease(request.chronicDisease())
            .withFamilyHistory(request.familyHistory())
            .withGeneral(request.general())
            .withHeadNeck(request.headNeck())
            .withChest(request.chest())
            .withHeart(request.heart())
            .withLungs(request.lungs())
            .withAbdomen(request.abdomen())
            .withSkinOfExtremities(request.skinOfExtremities())
            .withImpression(request.impression())
            .build();
  }
}
