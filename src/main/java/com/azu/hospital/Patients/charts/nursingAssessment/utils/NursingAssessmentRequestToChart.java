package com.azu.hospital.Patients.charts.nursingAssessment.utils;


import com.azu.hospital.Patients.charts.nursingAssessment.request.NursingAssessmentChartRequest;
import com.azu.hospital.Patients.charts.nursingAssessment.entity.NursingAssessmentChart;

public class NursingAssessmentRequestToChart {
    public static NursingAssessmentChart getNursingAssessmentChartPrivate(NursingAssessmentChartRequest request) {
        return new NursingAssessmentChart.Builder()
                .withLevelOfConsciousness(request.getLevelOfConsciousness())
                .withOrientation(request.getOrientation())
                .withMentalState(request.getMentalState())
                .withEyes(request.getEyes())
                .withEars(request.getEars())
                .withMouth(request.getMouth())
                .withNose(request.getNose())
                .withHair(request.getHair())
                .withNeck(request.getNeck())
                .withSkin(request.getSkin())
                .withChest(request.getChest())
                .withAbdomen(request.getAbdomen())
                .withUpperExtremities(request.getUpperExtremities())
                .withLowerExtremities(request.getLowerExtremities())
                .withNote(request.getNote())
                .withPainLevel(request.getPainLevel())
                .build();
    }
}
