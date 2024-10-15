package com.azu.hospital.Patients.charts.vitalSignChart.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.vitalSignChart.entity.VitalSign;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VitalSignDtoMapper {
  public VitalSignDto toDto(VitalSign vitalSign) {
    return Optional.ofNullable(vitalSign)
            .map(v -> new VitalSignDto(
                    v.getId(),
                    v.getChartName(),
                    v.getPainLevel(),
                    v.getRespiratoryRate(),
                    v.getSystolicBloodPressure(),
                    v.getDiastolicBloodPressure(),
                    v.getMainBloodPressure(),
                    v.getSkinTemperature(),
                    v.getCoreTemperature(),
                    v.getPulseRate(),
                    v.getCreatedAt(),
                    v.getSpo(),
                    v.getBloodSugar(),
                    v.getCreatedBy(),
                    v.getLastModifiedBy()
            ))
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "There is no data for return"
                    )
            );
  }
  public DtoForReturnIdOnly toResponseDto(VitalSign vitalSign){
    return new DtoForReturnIdOnly(vitalSign.getId());
  }

}
