package com.azu.hospital.Patients.charts.preAnestheticEvaluation.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.entity.PreAnestheticEvaluationChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreAnestheticEvaluationChartDtoMapper implements BaseChartDtoMapperInterface {
    public PreAnestheticEvaluationChartDto anestheticEvaluationChartToDTO(PreAnestheticEvaluationChart preAnestheticEvaluationChart) {
        return Optional.ofNullable(preAnestheticEvaluationChart)
                .map(c -> new PreAnestheticEvaluationChartDto(
                        c.getId(),
                        c.getChartName(),
                        c.getProposedProcedure(),
                        c.getPhysicalExam(),
                        c.getLabEcg(),
                        c.getAsa(),
                        c.getPlan(),
                        c.getAnesthesiologist(),
                        c.getDate(),
                        c.getTime(),
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
        PreAnestheticEvaluationChart newChart = (PreAnestheticEvaluationChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        PreAnestheticEvaluationChartDto dto = new PreAnestheticEvaluationChartDto();
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
        dto.setProposedProcedure(newChart.getProposedProcedure());
        dto.setPhysicalExam(newChart.getPhysicalExam());
        dto.setLabsEcg(newChart.getLabEcg());
        dto.setAsa(newChart.getAsa());
        dto.setPlan(newChart.getPlan());
        dto.setAnesthesiologist(newChart.getAnesthesiologist());
        dto.setDate(newChart.getDate());
        dto.setTime(newChart.getTime());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());

        return dto;
    }

    @Override
    public Class<PreAnestheticEvaluationChart> getMappedClass() {
        return PreAnestheticEvaluationChart.class;
    }
}
