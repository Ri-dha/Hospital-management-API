package com.azu.hospital.Patients.charts.base_chart.dto;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;

public interface BaseChartDtoMapperInterface {

    BaseChartsDto mapToDto(BaseCharts baseCharts);

    Class<?> getMappedClass();

}
