package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto;

import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientExperiencingEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientExperiencingDto {
  private Long id;

  private PatientExperiencingEnum experience;

  private Boolean isChecked;

  private String note;


  public PatientExperiencingDto() {
  }

  public PatientExperiencingDto(
          Long id,
          PatientExperiencingEnum experience,
          Boolean isChecked,
          String note
  ) {
    this.id = id;
    this.experience = experience;
    this.isChecked = isChecked;
    this.note = note;

  }
}
