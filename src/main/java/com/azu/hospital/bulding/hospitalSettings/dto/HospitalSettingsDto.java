package com.azu.hospital.bulding.hospitalSettings.dto;

import lombok.Data;

@Data
public class HospitalSettingsDto {
  private String hospitalName;

  public HospitalSettingsDto(String hospitalName){
    this.hospitalName = hospitalName;
  }
}
