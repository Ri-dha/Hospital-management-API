package com.azu.hospital.Patients.charts.nursingAssessment.dto;


import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.nursingAssessment.entity.NursingAssessmentChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NursingAssessmentChartDtoMapper implements BaseChartDtoMapperInterface {

    public NursingAssessmentChartDto chartDto(NursingAssessmentChart assessmentChart) {
        return Optional.ofNullable(assessmentChart)
                .map(c -> new NursingAssessmentChartDto(
                        c.getId(),
                        c.getChartName(),
                        c.getLevelOfConsciousness(),
                        c.getOrientation(),
                        c.getMentalState(),
                        c.getEyes(),
                        c.getEars(),
                        c.getMouth(),
                        c.getNose(),
                        c.getHair(),
                        c.getNeck(),
                        c.getSkin(),
                        c.getChest(),
                        c.getAbdomen(),
                        c.getUpperExtremities(),
                        c.getLowerExtremities(),
                        c.getNote(),
                        c.getPainLevel(),
                        c.getCreatedBy(),
                        c.getLastModifiedBy()
                ))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "There is not data for returning"
                        )
                );
    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        NursingAssessmentChart newChart = (NursingAssessmentChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        NursingAssessmentChartDto dto = new NursingAssessmentChartDto();
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
        dto.setLevelOfConsciousness(newChart.getLevelOfConsciousness());
        dto.setOrientation(newChart.getOrientation());
        dto.setMentalState(newChart.getMentalState());
        dto.setEyes(newChart.getEyes());
        dto.setEars(newChart.getEars());
        dto.setMouth(newChart.getMouth());
        dto.setNose(newChart.getNose());
        dto.setHair(newChart.getHair());
        dto.setNeck(newChart.getNeck());
        dto.setSkin(newChart.getSkin());
        dto.setChest(newChart.getChest());
        dto.setAbdomen(newChart.getAbdomen());
        dto.setUpperExtremities(newChart.getUpperExtremities());
        dto.setLowerExtremities(newChart.getLowerExtremities());
        dto.setNote(newChart.getNote());
        dto.setPainLevel(newChart.getPainLevel());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;
    }

    @Override
    public Class<NursingAssessmentChart> getMappedClass() {
        return NursingAssessmentChart.class;
    }
}
