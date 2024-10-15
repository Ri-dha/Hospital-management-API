package com.azu.hospital.bulding.hospitalSettings.service;

import com.azu.hospital.bulding.hospitalSettings.dao.HospitalSettings;
import com.azu.hospital.bulding.hospitalSettings.dao.HospitalSettingsDao;
import com.azu.hospital.bulding.hospitalSettings.dto.HospitalSettingsDto;
import com.azu.hospital.bulding.hospitalSettings.dto.HospitalSettingsDtoMapper;
import com.azu.hospital.bulding.hospitalSettings.request.HospitalSettingsRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("HospitalSettingsService")
public class HospitalSettingsService {
  private final HospitalSettingsDao dao;
  private final HospitalSettingsDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;



    public HospitalSettingsService(
            @Qualifier("HospitalSettingsJpaDataAccess")
          HospitalSettingsDao dao,
            HospitalSettingsDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
    this.dao = dao;
    this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

  public HospitalSettingsDto getHospitalSettings(){
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
    HospitalSettings settings = dao.getHospitalSettings()
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );
    return dtoMapper.toDto(settings);
  }
  public HospitalSettingsDto updateHospitalSettings(HospitalSettingsRequest request){
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
    HospitalSettings settings = dao.getHospitalSettings()
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

    if(request.hospitalName() != null && !request.hospitalName().equals(settings.getHospitalName())){
      settings.setHospitalName(request.hospitalName());
    }

    HospitalSettings update = dao.update(settings);

    return dtoMapper.toDto(update);
  }

}
