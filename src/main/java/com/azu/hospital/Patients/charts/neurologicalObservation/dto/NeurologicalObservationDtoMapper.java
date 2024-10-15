package com.azu.hospital.Patients.charts.neurologicalObservation.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.neurologicalObservation.entities.NeurologicalObservationChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import org.springframework.stereotype.Service;

@Service
public class NeurologicalObservationDtoMapper implements BaseChartDtoMapperInterface {
    public NeurologicalObservationDto chartToDto(NeurologicalObservationChart chart) {
        return new NeurologicalObservationDto(
                chart.getId(),
                chart.getChartName(),
                chart.getDate(),
                chart.getCandidateName(),
                chart.getAdult(),
                chart.getChild(),
                chart.getNote(),
                chart.getCreatedBy(),
                chart.getLastModifiedBy()
        );
    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        NeurologicalObservationChart newChart = (NeurologicalObservationChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        NeurologicalObservationDto dto = new NeurologicalObservationDto();
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
        dto.setDate(newChart.getDate());
        dto.setCandidateName(newChart.getCandidateName());
        dto.setAdult(newChart.getAdult());
        dto.setChild(newChart.getChild());
        dto.setNote(newChart.getNote());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;
    }

    @Override
    public Class<NeurologicalObservationChart> getMappedClass() {
        return NeurologicalObservationChart.class;
    }
}
