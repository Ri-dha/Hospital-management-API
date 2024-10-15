package com.azu.hospital.Patients.charts.allPatientCharts.dto;

import com.azu.hospital.utils.enums.patient.ChartTypes;
import lombok.Data;

@Data
public class AllPatientChartsDto {

  private ChartTypes chartTypes;
  public AllPatientChartsDto() {
  }

  public AllPatientChartsDto(
        ChartTypes chartTypes
  ) {
  this.chartTypes = chartTypes;
  }
}
