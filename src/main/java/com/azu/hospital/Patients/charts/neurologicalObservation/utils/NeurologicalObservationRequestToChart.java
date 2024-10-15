package com.azu.hospital.Patients.charts.neurologicalObservation.utils;


import com.azu.hospital.Patients.charts.neurologicalObservation.request.NeurologicalObservationRequest;
import com.azu.hospital.Patients.charts.neurologicalObservation.entities.NeurologicalObservationChart;

public class NeurologicalObservationRequestToChart {
  public static NeurologicalObservationChart requestToChart(NeurologicalObservationRequest request){
    return new NeurologicalObservationChart.Builder()
            .withDate(String.valueOf(request.getDate()))
            .withCandidateName(request.getCandidateName())
            .withChild(request.getChild())
            .withAdult(request.getAdult())
            .withNote(request.getNote())
            .build();
  }
}
