package com.azu.hospital.Patients.charts.allPatientCharts.dto;

import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class AllPatientChartsDtoMapper implements Function<AllPatientChart,AllPatientChartsDto> {

    @Override
    public AllPatientChartsDto apply(AllPatientChart allPatientChart) {
        return new AllPatientChartsDto(
                allPatientChart.getChartType()
        );
    }
}

