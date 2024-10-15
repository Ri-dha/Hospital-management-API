package com.azu.hospital.bulding.hospitalSettings.dto;

import com.azu.hospital.bulding.hospitalSettings.dao.HospitalSettings;
import org.springframework.stereotype.Service;

@Service
public class HospitalSettingsDtoMapper {
  public HospitalSettingsDto toDto(HospitalSettings settings){
    return new HospitalSettingsDto(
            settings.getHospitalName()
    );
  }
}
