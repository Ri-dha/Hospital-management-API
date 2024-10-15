package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EyeCare {
  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean ointment;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean saline;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean shields;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean taped;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean goggles;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean pads;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean closed;

  public EyeCare() {
  }

  public EyeCare(
          Boolean ointment,
          Boolean saline,
          Boolean shields,
          Boolean taped,
          Boolean goggles,
          Boolean pads,
          Boolean closed
  ) {
    this.ointment = ointment;
    this.saline = saline;
    this.shields = shields;
    this.taped = taped;
    this.goggles = goggles;
    this.pads = pads;
    this.closed = closed;
  }
}
