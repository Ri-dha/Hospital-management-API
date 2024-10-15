package com.azu.hospital.Patients.charts.vitalSignChart.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VitalSignChartDtoSpecial {

  private Long id;

  private BigDecimal painLevel;

  private Integer respiratoryRate;

  private Integer systolicBloodPressure;

  private Integer diastolicBloodPressure;

  private Integer mainBloodPressure;

  private Float skinTemperature;



  public VitalSignChartDtoSpecial(Long vitalSignId, Integer respiratoryRate, Integer systolicBloodPressure, Integer diastolicBloodPressure, Integer mainBloodPressure, Float skinTemperature) {
    this.id = vitalSignId;
    this.respiratoryRate = respiratoryRate;
    this.systolicBloodPressure = systolicBloodPressure;
    this.diastolicBloodPressure = diastolicBloodPressure;
    this.mainBloodPressure = mainBloodPressure;
    this.skinTemperature = skinTemperature;
  }
}
