package com.azu.hospital.Patients.charts.physical_assment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Turgor {
  @Column(name = "turgor_elastic")
  private Boolean elastic = false;
  @Column(name = "turgor_tented")
  private Boolean tented = false;
  @Column(name = "turgor_edema")
  private Boolean edema = false;

  public Turgor() {
  }

  public Turgor(Boolean elastic, Boolean tented, Boolean edema) {
    this.elastic = elastic;
    this.tented = tented;
    this.edema = edema;
  }
}
