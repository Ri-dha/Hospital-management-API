package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PreProcedure {
  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isPtIdentified;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isConsent;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isChartReviewed;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isNpoSince;

  public PreProcedure() {
  }

  public PreProcedure(
          Boolean isPtIdentified,
          Boolean isConsent,
          Boolean isChartReviewed,
          Boolean isNpoSince
  ) {
    this.isPtIdentified = isPtIdentified;
    this.isConsent = isConsent;
    this.isChartReviewed = isChartReviewed;
    this.isNpoSince = isNpoSince;
  }
}
