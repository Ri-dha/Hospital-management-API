package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AnestheticTechnique {
  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean intravenous;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean inhalation;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean intramuscular;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean preOxygenation;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean lta;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean rapidSequence;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean rectal;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean cricoidPressure;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean spinal;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean epidural;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean axillary;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean bierBlock;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean ankleBlock;

  private String position;

  private String local;

  private String dose;

  private String needle;

  private String anestheticSite;

  private String anestheticAttempts;

  @Column(columnDefinition = "TEXT")
  private String otherNotes;

  public AnestheticTechnique() {
  }

  public AnestheticTechnique(
          Boolean intravenous,
          Boolean inhalation,
          Boolean intramuscular,
          Boolean preOxygenation,
          Boolean lta,
          Boolean rapidSequence,
          Boolean rectal,
          Boolean cricoidPressure,
          Boolean spinal,
          Boolean epidural,
          Boolean axillary,
          Boolean bierBlock,
          Boolean ankleBlock,
          String position,
          String local,
          String dose,
          String needle,
          String anestheticSite,
          String anestheticAttempts,
          String otherNotes
  ) {
    this.intravenous = intravenous;
    this.inhalation = inhalation;
    this.intramuscular = intramuscular;
    this.preOxygenation = preOxygenation;
    this.lta = lta;
    this.rapidSequence = rapidSequence;
    this.rectal = rectal;
    this.cricoidPressure = cricoidPressure;
    this.spinal = spinal;
    this.epidural = epidural;
    this.axillary = axillary;
    this.bierBlock = bierBlock;
    this.ankleBlock = ankleBlock;
    this.position = position;
    this.local = local;
    this.dose = dose;
    this.needle = needle;
    this.anestheticSite = anestheticSite;
    this.anestheticAttempts = anestheticAttempts;
    this.otherNotes = otherNotes;
  }
}
