package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.AirIntubation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirwayManagement {
  private AirIntubation airIntubation;

  private String tubeSize;

  private String blade;

  private String attempts;

  private String securedAt;

  private String breathSounds;

  @Column(columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
  @NotNull(message = "Determined if cuffed or unCuffed")
  private Boolean isCuffed;

  private Boolean isUnCuffed = false;

  private Boolean isOral = false;

  private Boolean isNasal = false;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isCircle;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isNrb;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isMaskCase;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isViaLMA;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isNasalCannula;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isSimpleO2Mask;

  public AirwayManagement() {
  }

  public AirwayManagement(
          AirIntubation airIntubation,
          String tubeSize,
          String blade,
          String attempts,
          String securedAt,
          String breathSounds,
          Boolean isCuffed,
          Boolean isUnCuffed,
          Boolean isOral,
          Boolean isNasal,
          Boolean isCircle,
          Boolean isNrb,
          Boolean isMaskCase,
          Boolean isViaLMA,
          Boolean isNasalCannula,
          Boolean isSimpleO2Mask
  ) {
    this.airIntubation = airIntubation;
    this.tubeSize = tubeSize;
    this.blade = blade;
    this.attempts = attempts;
    this.securedAt = securedAt;
    this.breathSounds = breathSounds;
    this.isCuffed = isCuffed;
    this.isUnCuffed = isUnCuffed;
    this.isOral = isOral;
    this.isNasal = isNasal;
    this.isCircle = isCircle;
    this.isNrb = isNrb;
    this.isMaskCase = isMaskCase;
    this.isViaLMA = isViaLMA;
    this.isNasalCannula = isNasalCannula;
    this.isSimpleO2Mask = isSimpleO2Mask;
  }
}
