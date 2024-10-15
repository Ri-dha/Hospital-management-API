package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PreAnesthesiaState {
  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean awake;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean apprehensive;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean uncooperative;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean asleep;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean confused;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean unresponsive;

  public PreAnesthesiaState() {
  }

  public PreAnesthesiaState(
          Boolean awake,
          Boolean apprehensive,
          Boolean uncooperative,
          Boolean asleep,
          Boolean confused,
          Boolean unresponsive
  ) {
    this.awake = awake;
    this.apprehensive = apprehensive;
    this.uncooperative = uncooperative;
    this.asleep = asleep;
    this.confused = confused;
    this.unresponsive = unresponsive;
  }
}
