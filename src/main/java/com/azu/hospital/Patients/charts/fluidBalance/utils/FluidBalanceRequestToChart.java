package com.azu.hospital.Patients.charts.fluidBalance.utils;

import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidBalanceChart;
import com.azu.hospital.Patients.charts.fluidBalance.request.FluidBalanceRequest;

public class FluidBalanceRequestToChart {
  public static FluidBalanceChart requestToChart(FluidBalanceRequest request){
    return new FluidBalanceChart.Builder()
            .withDateFrom(request.dateFrom())
            .withDateTo(request.dateTo())
            .withMl24InputHour(request.ml24InputHour())
            .withFluidBalance24InputHour(request.fluidBalance24InputHour())
            .withCandidate(request.candidate())
            .build();
  }
}
