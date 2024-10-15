package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.NonSedatedPatientAssessment;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NonSedatedPatientAssessmentDtoMapper implements BaseChartDtoMapperInterface {
    public NonSedatedPatientAssessmentDto chartToDto(NonSedatedPatientAssessment chart) {
        return Optional.ofNullable(chart)
                .map(c -> new NonSedatedPatientAssessmentDto(
                        c.getId(),
                        c.getChartName(),
                        c.getPatientPositions(),
                        c.getPatientLimitation(),
                        c.getProcedureArea(),
                        c.getMedsPerMD(),
                        c.getMedsPerMDList(),
                        c.getMedsPerMDListOther(),
                        c.getNursesNotes(),
                        c.getPostOperativeDiagnosis(),
                        c.getProcedurePerformed(),
                        c.getTimeStart(),
                        c.getTimeFinish(),
                        c.getPatient().lastMedicalHistory().getDrugHistoryAllergy(),
                        c.getCreatedBy(),
                        c.getLastModifiedBy()
                ))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "There is no data for returning"
                        )
                );
    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        NonSedatedPatientAssessment newChart = (NonSedatedPatientAssessment) baseCharts;
        if (newChart == null) {
            return null;
        }
        NonSedatedPatientAssessmentDto dto = new NonSedatedPatientAssessmentDto();
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
        dto.setPatientPositions(newChart.getPatientPositions());
        dto.setPatientLimitation(newChart.getPatientLimitation());
        dto.setProcedureArea(newChart.getProcedureArea());
        dto.setMedsPerMD(newChart.getMedsPerMD());
        dto.setMedsPerMDList(newChart.getMedsPerMDList());
        dto.setMedsPerMDListOther(newChart.getMedsPerMDListOther());
        dto.setNursesNotes(newChart.getNursesNotes());
        dto.setPostOperativeDiagnosis(newChart.getPostOperativeDiagnosis());
        dto.setProcedurePerformed(newChart.getProcedurePerformed());
        dto.setTimeStart(newChart.getTimeStart());
        dto.setTimeFinish(newChart.getTimeFinish());
        dto.setAllergy(newChart.getPatient().lastMedicalHistory().getDrugHistoryAllergy());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;
    }

    @Override
    public Class<NonSedatedPatientAssessment> getMappedClass() {
        return NonSedatedPatientAssessment.class;
    }
}
