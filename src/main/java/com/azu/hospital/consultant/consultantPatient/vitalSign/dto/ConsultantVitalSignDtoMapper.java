package com.azu.hospital.consultant.consultantPatient.vitalSign.dto;

import com.azu.hospital.consultant.consultantPatient.vitalSign.entity.ConsultantVitalSign;
import com.azu.hospital.consultant.consultantPatient.vitalSign.request.ConsultantUpdateVitalSignRequest;
import org.springframework.stereotype.Service;

@Service
public class ConsultantVitalSignDtoMapper {

    public ConsultantVitalSignDto toDto(ConsultantVitalSign vitalSign){
      return new ConsultantVitalSignDto(
              vitalSign.getId(),
              vitalSign.getConsultantPatient().getId(),
              vitalSign.getPulseRate(),
              vitalSign.getSysBloodPressure(),
              vitalSign.getDiaBloodPressure(),
              vitalSign.getBloodSugar(),
              vitalSign.getBodyTemp(),
              vitalSign.getSkinTemp(),
              vitalSign.getSpo(),
              vitalSign.getRespiratoryRate(),
              vitalSign.getPainLevel()
      );
    }
    public ConsultantVitalSignDtoEntity toEntity(ConsultantUpdateVitalSignRequest vitalSign){
        return new ConsultantVitalSignDtoEntity(
                vitalSign.getPulseRate(),
                vitalSign.getSysBloodPressure(),
                vitalSign.getDiaBloodPressure(),
                vitalSign.getBloodSugar(),
                vitalSign.getBodyTemp(),
                vitalSign.getSkinTemp(),
                vitalSign.getSpo(),
                vitalSign.getRespiratoryRate(),
                vitalSign.getPainLevel()
        );
    }

}
