package com.azu.hospital.Patients.charts.preAdvanceDirective.utils;


import com.azu.hospital.Patients.charts.preAdvanceDirective.request.PreAdvanceDirectiveRequest;
import com.azu.hospital.Patients.charts.preAdvanceDirective.entity.PreAdvanceDirectiveChart;

public class PreAdvanceDirectiveRequestToChart {
  public static PreAdvanceDirectiveChart requestToChart(PreAdvanceDirectiveRequest request){
    return new PreAdvanceDirectiveChart.Builder()
            .withOption(request.option())
            .withPrintName(request.printName())
            .withSignature(request.signature())
            .withDate(request.date())
            .withPreOpNurseSignature(request.preOpNurseSignature())
            .withPreOpNurseDate(request.preOpNurseDate())
            .withPreOpNurseTime(request.preOpNurseTime())
            .build();
  }
}
