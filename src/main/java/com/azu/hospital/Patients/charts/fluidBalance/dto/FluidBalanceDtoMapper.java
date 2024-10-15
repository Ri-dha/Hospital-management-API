package com.azu.hospital.Patients.charts.fluidBalance.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidBalanceChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FluidBalanceDtoMapper implements BaseChartDtoMapperInterface {
    public FluidBalanceDto chartToDto(FluidBalanceChart chart) {
        return Optional.ofNullable(chart)
                .map(c -> new FluidBalanceDto(
                        c.getId(),
                        c.getChartName(),
                        c.getDateFrom(),
                        c.getDateTo(),
                        c.getMl24InputHour(),
                        c.getFluidBalance24InputHour(),
                        c.getInputs(),
                        c.getOutputs(),
                        c.getCandidate(),
                        c.getCreatedBy(),
                        c.getLastModifiedBy()
                ))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "There Is No Data For Return"
                        )
                );
    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        FluidBalanceChart newChart = (FluidBalanceChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        FluidBalanceDto dto = new FluidBalanceDto();
        dto.setId(newChart.getId());
        dto.setPatientId(newChart.getPatient().getId());
        dto.setDoctorId(newChart.getPatient().getDoctorSpecials().getDoctor().getId());
        dto.setDoctorName(newChart.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial());
        dto.setPatientName(newChart.getPatient().getPatientData().getFullName());
        dto.setPatientAge(newChart.getPatient().getPatientDate().getAge());
        dto.setPatientGender(newChart.getPatient().getPatientData().getGender().name());
        dto.setWardName(newChart.getPatient().getWard().getName());
        dto.setBedNumber(newChart.getPatient().getBed().getBedNumber());
        dto.setChartName(newChart.getChartName());
        dto.setDateFrom(newChart.getDateFrom());
        dto.setDateTo(newChart.getDateTo());
        dto.setMl24InputHour(newChart.getMl24InputHour());
        dto.setFluidBalance24InputHour(newChart.getFluidBalance24InputHour());
        dto.setInputs(newChart.getInputs());
        dto.setOutputs(newChart.getOutputs());
        dto.setCandidate(newChart.getCandidate());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;

    }

    @Override
    public Class<FluidBalanceChart> getMappedClass() {
        return FluidBalanceChart.class;
    }
}
