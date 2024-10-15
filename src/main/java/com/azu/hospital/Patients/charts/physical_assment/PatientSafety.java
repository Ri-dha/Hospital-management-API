package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PatientSafety {
  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean anesthesiaMachineChecked;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean pressurePointsChecked;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean safetyBeltOn;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean armsTucked;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean axillaryRoll;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean restraints;

  public PatientSafety() {
  }

  public PatientSafety(
          Boolean anesthesiaMachineChecked,
          Boolean pressurePointsChecked,
          Boolean safetyBeltOn,
          Boolean armsTucked,
          Boolean axillaryRoll,
          Boolean restraints
  ) {
    this.anesthesiaMachineChecked = anesthesiaMachineChecked;
    this.pressurePointsChecked = pressurePointsChecked;
    this.safetyBeltOn = safetyBeltOn;
    this.armsTucked = armsTucked;
    this.axillaryRoll = axillaryRoll;
    this.restraints = restraints;
  }
}
