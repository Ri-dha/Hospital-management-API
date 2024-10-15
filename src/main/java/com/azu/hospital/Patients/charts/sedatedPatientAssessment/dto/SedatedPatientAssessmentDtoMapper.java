package com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities.SedatedPatientAssessment;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SedatedPatientAssessmentDtoMapper implements BaseChartDtoMapperInterface {
  public SedatedPatientAssessmentDto chartToDto(SedatedPatientAssessment chart) {
    return Optional.ofNullable(chart)
            .map(c -> new SedatedPatientAssessmentDto(
                    c.getId(),
                    c.getChartName(),
                    c.getMedications(),
                    c.getPositiveProblems(),
                    c.getPreOpMeds(),
                    c.getAsa(),
                    c.getPreProcedure(),
                    c.getPreAnesthesiaState(),
                    c.getPatientSafety(),
                    c.getEyeCare(),
                    c.getAnestheticTechnique(),
                    c.getMonitorsEquipment(),
                    c.getAirwayManagement(),
                    c.getAnesthesiaStartTime(),
                    c.getAnesthesiaEndTime(),
                    c.getProcedureStartTime(),
                    c.getProcedureEndTime(),
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
    public SedatedPatientAssessmentDto mapToDto(BaseCharts baseCharts) {
        SedatedPatientAssessment newChart = (SedatedPatientAssessment) baseCharts;
        if (newChart == null) {
            return null;
        }
        SedatedPatientAssessmentDto dto = new SedatedPatientAssessmentDto();

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
        dto.setMedications(newChart.getMedications());
        dto.setPositiveProblems(newChart.getPositiveProblems());
        dto.setPreOpMeds(newChart.getPreOpMeds());
        dto.setAsa(newChart.getAsa());
        dto.setPreProcedure(newChart.getPreProcedure());
        dto.setPreAnesthesiaState(newChart.getPreAnesthesiaState());
        dto.setPatientSafety(newChart.getPatientSafety());
        dto.setEyeCare(newChart.getEyeCare());
        dto.setPreAnesthesiaState(newChart.getPreAnesthesiaState());
        dto.setMonitorsEquipment(newChart.getMonitorsEquipment());
        dto.setAirwayManagement(newChart.getAirwayManagement());
        dto.setAnesthesiaStartTime(newChart.getAnesthesiaStartTime());
        dto.setAnesthesiaEndTime(newChart.getAnesthesiaEndTime());
        dto.setProcedureStartTime(newChart.getProcedureStartTime());
        dto.setProcedureEndTime(newChart.getProcedureEndTime());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;
    }

    @Override
    public Class<SedatedPatientAssessment> getMappedClass() {
        return SedatedPatientAssessment.class;
    }
}
