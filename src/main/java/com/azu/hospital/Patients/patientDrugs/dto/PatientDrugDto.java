package com.azu.hospital.Patients.patientDrugs.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDrugDto {
  private Long drugId;

  private String drug;

  private String dose;

  private String time;

  private String roa;

  private Integer count;

  private String note;

  public PatientDrugDto() {
  }

  public PatientDrugDto(
          Long drugId,
          String drug,
          String dose,
          String time,
          String roa,
          Integer count,
          String note
  ) {
    this.drugId = drugId;
    this.drug = drug;
    this.dose = dose;
    this.time = time;
    this.roa = roa;
    this.count = count;
    this.note = note;
  }
}
