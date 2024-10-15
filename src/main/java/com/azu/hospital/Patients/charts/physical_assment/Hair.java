package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Hair {
  private Boolean clean = false;
  private Boolean dirty = false;
  private Boolean neatlyGroomed = false;
  private Boolean thickFull = false;
  private Boolean sparseHairLoss = false;
  private Boolean evenlyDistributed = false;
  private Boolean unevenlyDistributed = false;
  @Column(name = "hair_note")
  private String note;

  public Hair() {
  }

  @JsonCreator
  public Hair(
          @JsonProperty("clean") Boolean clean,
          @JsonProperty("dirty") Boolean dirty,
          @JsonProperty("neatlyGroomed") Boolean neatlyGroomed,
          @JsonProperty("thickFull") Boolean thickFull,
          @JsonProperty("sparseHairLoss") Boolean sparseHairLoss,
          @JsonProperty("evenlyDistributed") Boolean evenlyDistributed,
          @JsonProperty("unevenlyDistributed") Boolean unevenlyDistributed,
          @JsonProperty("note") String note
  ) {
    this.clean = clean;
    this.dirty = dirty;
    this.neatlyGroomed = neatlyGroomed;
    this.thickFull = thickFull;
    this.sparseHairLoss = sparseHairLoss;
    this.evenlyDistributed = evenlyDistributed;
    this.unevenlyDistributed = unevenlyDistributed;
    this.note = note;
  }
}
