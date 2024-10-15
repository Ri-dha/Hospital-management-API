package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SocialHistory {
  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean pregnant;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean birthControl;

  @Column(columnDefinition = "TEXT")
  private String familyHx;

  private String otherSocialHistory;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean ETOH;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean smokingStatus;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean counselingProvided;

  @Column(columnDefinition = "TEXT")
  private String otherSubstanceAbuse;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean jehovahWitness;

  public SocialHistory() {
  }

  public SocialHistory(
          Boolean pregnant,
          Boolean birthControl,
          String familyHx,
          String otherSocialHistory,
          Boolean ETOH,
          Boolean smokingStatus,
          Boolean counselingProvided,
          String otherSubstanceAbuse,
          Boolean jehovahWitness
  ) {
    this.pregnant = pregnant;
    this.birthControl = birthControl;
    this.familyHx = familyHx;
    this.otherSocialHistory = otherSocialHistory;
    this.ETOH = ETOH;
    this.smokingStatus = smokingStatus;
    this.counselingProvided = counselingProvided;
    this.otherSubstanceAbuse = otherSubstanceAbuse;
    this.jehovahWitness = jehovahWitness;
  }
}
