package com.azu.hospital.Patients.charts.preAdvanceDirective.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.preAdvanceDirective.entity.PreAdvanceDirectiveChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreAdvanceDirectiveDtoMapper implements BaseChartDtoMapperInterface {
  public PreAdvanceDirectiveDto chartToDto(PreAdvanceDirectiveChart chart){
    return Optional.ofNullable(chart)
            .map(c -> new PreAdvanceDirectiveDto(
                    c.getId(),
                    c.getChartName(),
                    c.getOption(),
                    c.getPrintName(),
                    c.getSignature(),
                    c.getDate(),
                    c.getPreOpNurseSignature(),
                    c.getPreOpNurseDate(),
                    c.getPreOpNurseTime(),
                    c.getCreatedBy(),
                    c.getLastModifiedBy()
            ))
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "There Is No Data For Returning"
                    )
            );
  }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        PreAdvanceDirectiveChart newChart = (PreAdvanceDirectiveChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        PreAdvanceDirectiveDto dto = new PreAdvanceDirectiveDto();
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
        dto.setOption(newChart.getOption());
        dto.setPrintName(newChart.getPrintName());
        dto.setSignature(newChart.getSignature());
        dto.setDate(newChart.getDate());
        dto.setPreOpNurseSignature(newChart.getPreOpNurseSignature());
        dto.setPreOpNurseDate(newChart.getPreOpNurseDate());
        dto.setPreOpNurseTime(newChart.getPreOpNurseTime());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());

        return dto;
    }

    @Override
    public Class<PreAdvanceDirectiveChart> getMappedClass() {
        return PreAdvanceDirectiveChart.class;
    }
}
