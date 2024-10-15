package com.azu.hospital.Patients.charts.vitalSignChart.utils;

import com.azu.hospital.Patients.charts.vitalSignChart.entity.VitalSign;
import com.azu.hospital.Patients.charts.vitalSignChart.request.VitalSignRequest;

public class VitalSignRequestToEntity {
  public static VitalSign toEntity(VitalSignRequest request){
    return new VitalSign(
            request.painLevel(),
            request.respiratoryRate(),
            request.systolicBloodPressure(),
            request.diastolicBloodPressure(),
            request.mainBloodPressure(),
            request.skinTemperature(),
            request.coreTemperature(),
            request.pulseRate(),
            request.spo() ,
            request.bloodSugar()
    );
  }
}
