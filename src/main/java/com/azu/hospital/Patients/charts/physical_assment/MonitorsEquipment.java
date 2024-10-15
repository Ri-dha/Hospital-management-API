package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MonitorsEquipment {
  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isPrecordial;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isEsophogeal;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isLeft;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isRight;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isContinuousEcg;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isPulseOximeter;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isNgOgTube;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isEndTidalCO2;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isFluidWarmer;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isVLeadsEcg;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isGasAnalyzer;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isNerveStimulator;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isO2Sensor;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isFoleyCatheter;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isAirwayHumidifier;

  private String temp;

  private String ivs;

  private String site;

  private String gauge;

  public MonitorsEquipment() {
  }

  public MonitorsEquipment(
          Boolean isPrecordial,
          Boolean isEsophogeal,
          Boolean isLeft,
          Boolean isRight,
          Boolean isContinuousEcg,
          Boolean isPulseOximeter,
          Boolean isNgOgTube,
          Boolean isEndTidalCO2,
          Boolean isFluidWarmer,
          Boolean isVLeadsEcg,
          Boolean isGasAnalyzer,
          Boolean isNerveStimulator,
          Boolean isO2Sensor,
          Boolean isFoleyCatheter,
          Boolean isAirwayHumidifier,
          String temp,
          String ivs,
          String site,
          String gauge
  ) {
    this.isPrecordial = isPrecordial;
    this.isEsophogeal = isEsophogeal;
    this.isLeft = isLeft;
    this.isRight = isRight;
    this.isContinuousEcg = isContinuousEcg;
    this.isPulseOximeter = isPulseOximeter;
    this.isNgOgTube = isNgOgTube;
    this.isEndTidalCO2 = isEndTidalCO2;
    this.isFluidWarmer = isFluidWarmer;
    this.isVLeadsEcg = isVLeadsEcg;
    this.isGasAnalyzer = isGasAnalyzer;
    this.isNerveStimulator = isNerveStimulator;
    this.isO2Sensor = isO2Sensor;
    this.isFoleyCatheter = isFoleyCatheter;
    this.isAirwayHumidifier = isAirwayHumidifier;
    this.temp = temp;
    this.ivs = ivs;
    this.site = site;
    this.gauge = gauge;
  }
}
