package com.azu.hospital.Patients.charts.base_chart.dto;


import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.discharge.dto.DischargeDto;
import com.azu.hospital.Patients.charts.discharge.dto.DischargeDtoMapper;
import com.azu.hospital.Patients.charts.discharge.entity.DischargeChart;
import com.azu.hospital.Patients.charts.doctorTour.dto.DoctorTourDto;
import com.azu.hospital.Patients.charts.doctorTour.dto.DoctorTourDtoMapper;
import com.azu.hospital.Patients.charts.doctorTour.entity.DoctorTourChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class BaseChartsDtoMapper implements Function<BaseCharts, BaseChartsDto>  , BaseChartDtoMapperInterface {
    @Override
    public BaseChartsDto apply(BaseCharts baseCharts) {
        return new BaseChartsDto(
                baseCharts.getId(),
                baseCharts.getPatient().getId(),
                baseCharts.getBaseUser().getId(),
                baseCharts.getBaseUser().getUsernameSpecial(),
                baseCharts.getPatient().getPatientData().getFullName(),
                baseCharts.getPatient().getPatientDate().getAge(),
                baseCharts.getPatient().getPatientData().getGender().name(),
                baseCharts.getPatient().getWard().getName(),
                baseCharts.getPatient().getBed().getBedNumber(),
                baseCharts.getChartName()

        );
    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        BaseChartsDto baseChartsDto = new BaseChartsDto(
                baseCharts.getId(),
                baseCharts.getPatient().getId(),
                baseCharts.getBaseUser().getId(),
                baseCharts.getBaseUser().getUsernameSpecial(),
                baseCharts.getPatient().getPatientData().getFullName(),
                baseCharts.getPatient().getPatientDate().getAge(),
                baseCharts.getPatient().getPatientData().getGender().name(),
                baseCharts.getPatient().getWard().getName(),
                baseCharts.getPatient().getBed().getBedNumber(),
                baseCharts.getChartName()

        );
        return baseChartsDto;
    }

    @Override
    public Class<BaseCharts> getMappedClass() {
        return BaseCharts.class;
    }
}

