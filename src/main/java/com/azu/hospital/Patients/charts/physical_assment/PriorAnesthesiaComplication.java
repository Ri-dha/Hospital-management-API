package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PriorAnesthesiaComplication {
  private Boolean substanceAbuse;
  @Column(columnDefinition = "TEXT")
  private String priorAnesthesiaNote;

  public PriorAnesthesiaComplication() {
  }
}
