package com.azu.hospital.Patients.charts.painManagment.utils;

import com.azu.hospital.Patients.charts.painManagment.entity.PainManagementChart;
import com.azu.hospital.Patients.charts.painManagment.request.PainManagementRequest;

public class PainManagementRequestToChart {
  public static PainManagementChart requestToChart(PainManagementRequest request){
    return new PainManagementChart.Builder()
            .withPainFeel(request.painFeel())
            .withPainFeelRadiating(request.painFeelRadiating())
            .withPainGoAnywhereElse(request.painGoAnywhereElse())
            .withMajorLifeChanges(request.majorLifeChanges())
            .withPainStatus(request.painStatus())
            .withPainStarted(request.painStarted())
            .withPainStartDetails(request.painStartDetails())
            .withPainWorse(request.painWorse())
            .withPainWorseOther(request.painWorseOther())
            .withPainBetter(request.painBetter())
            .withPainBetterOther(request.painBetterOther())
            .withTimePainGetWorse(request.timePainGetWorse())
            .withTimePainGetWorseOther(request.timePainGetWorseOther())
            .withPainDescribes(request.painDescribes())
            .withPainInterruptSleep(request.painInterruptSleep())
            .withTriedTreatments(request.triedTreatments())
            .withLastYearTest(request.lastYearTest())
            .withOtherLastYearTest(request.otherLastYearTest())
            .build();
  }
}
