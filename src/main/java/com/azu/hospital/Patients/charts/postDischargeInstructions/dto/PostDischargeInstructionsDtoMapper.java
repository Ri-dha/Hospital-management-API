package com.azu.hospital.Patients.charts.postDischargeInstructions.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.postDischargeInstructions.entity.PostDischargeInstructionsChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostDischargeInstructionsDtoMapper implements BaseChartDtoMapperInterface {
    public PostDischargeInstructionsDto chartToDto(PostDischargeInstructionsChart chart) {
        return Optional.ofNullable(chart)
                .map(c -> new PostDischargeInstructionsDto(
                        c.getId(),
                        c.getChartName(),
                        c.getAnticoagulantAdvisement(),
                        c.getDoctorName(),
                        c.getPhoneNumber(),
                        c.getHospitalName(),
                        c.getPatientSignature(),
                        c.getPatientSignatureDate(),
                        c.getPatientSignatureTime(),
                        c.getRegisteredNurseName(),
                        c.getRegisteredDate(),
                        c.getRegisteredTime(),
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
        PostDischargeInstructionsChart newChart = (PostDischargeInstructionsChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        PostDischargeInstructionsDto dto = new PostDischargeInstructionsDto();
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
        dto.setAnticoagulantAdvisement(newChart.getAnticoagulantAdvisement());
        dto.setDoctorName(newChart.getDoctorName());
        dto.setPhoneNumber(newChart.getPhoneNumber());
        dto.setHospitalName(newChart.getHospitalName());
        dto.setPatientSignature(newChart.getPatientSignature());
        dto.setPatientSignatureDate(newChart.getPatientSignatureDate());
        dto.setPatientSignatureTime(newChart.getPatientSignatureTime());
        dto.setRegisteredNurseName(newChart.getRegisteredNurseName());
        dto.setRegisteredDate(newChart.getRegisteredDate());
        dto.setRegisteredTime(newChart.getRegisteredTime());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;
    }

    @Override
    public Class<PostDischargeInstructionsChart> getMappedClass() {
        return PostDischargeInstructionsChart.class;
    }
}
