package com.azu.hospital.Patients.charts.discharge.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.discharge.entity.DischargeChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DischargeDtoMapper implements BaseChartDtoMapperInterface {



    public DischargeDto chartToDto(DischargeChart chart) {
        return Optional.ofNullable(chart)
                .map(c -> new DischargeDto(
                        c.getId(),
                        c.getChartName(),
                        c.getDischargedDate(),
                        c.getDischargeTime(),
                        c.getTreatmentOnDischarge(),
                        c.getRecommendation(),
                        c.getSurgicalMedicalProcedure(),
                        c.getPatient().getPatientDate().getAdmissionDate(),
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
        DischargeChart newChart = (DischargeChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        DischargeDto dto = new DischargeDto();
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
        dto.setDischargeDate(newChart.getDischargedDate());
        dto.setDischargeTime(newChart.getDischargeTime());
        dto.setTreatmentOnDischarge(newChart.getTreatmentOnDischarge());
        dto.setRecommendation(newChart.getRecommendation());
        dto.setSurgicalMedicalProcedure(newChart.getSurgicalMedicalProcedure());
        dto.setAdmissionDate(newChart.getPatient().getPatientDate().getAdmissionDate());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;

    }

    @Override
    public Class<DischargeChart> getMappedClass() {
        return DischargeChart.class;
    }
}
