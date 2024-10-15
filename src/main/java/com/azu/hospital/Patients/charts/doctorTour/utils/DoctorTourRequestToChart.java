package com.azu.hospital.Patients.charts.doctorTour.utils;

import com.azu.hospital.Patients.charts.doctorTour.entity.DoctorTourChart;
import com.azu.hospital.Patients.charts.doctorTour.request.DoctorTourRequest;

public class DoctorTourRequestToChart {
  public static DoctorTourChart requestToChart(DoctorTourRequest request){
    return new DoctorTourChart.Builder()
            .withDate(request.date())
            .withTime(request.time())
            .withBmi(request.bmi())
            .withMedicalDx(request.medicalDx())
            .withTourDetails(request.tourDetails())
            .withFollowUpNote(request.followUpNote())
            .withIsMaskOn(request.isMaskOn())
            .withShift((request.shift()))
            .build();
  }
}
