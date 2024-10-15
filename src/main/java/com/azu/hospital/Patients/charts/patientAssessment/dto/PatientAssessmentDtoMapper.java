package com.azu.hospital.Patients.charts.patientAssessment.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.patientAssessment.entity.PatientAssessmentChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientAssessmentDtoMapper implements BaseChartDtoMapperInterface {
    public PatientAssessmentDto chartDto(PatientAssessmentChart chart){
        return Optional.ofNullable(chart)
                .map(c -> new PatientAssessmentDto(
                        c.getId(),
                        c.getChartName(),
                        c.getDiagnosis(),
                        c.getProcedures(),
                        c.getSkinCondition(),
                        c.getPhysicalLimitations(),
                        c.getLoc(),
                        c.getIvSite(),
                        c.getPreProcedureChecklist(),
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
        PatientAssessmentChart newChart = (PatientAssessmentChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        PatientAssessmentDto dto = new PatientAssessmentDto();
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
        dto.setDiagnosis(newChart.getDiagnosis());
        dto.setProcedures(newChart.getProcedures());
        dto.setSkinCondition(newChart.getSkinCondition());
        dto.setPhysicalLimitations(newChart.getPhysicalLimitations());
        dto.setLoc(newChart.getLoc());
        dto.setIvSite(newChart.getIvSite());
        dto.setPreProcedureChecklist(newChart.getPreProcedureChecklist());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;
    }

    @Override
    public Class<PatientAssessmentChart> getMappedClass() {
        return PatientAssessmentChart.class;
    }
}
