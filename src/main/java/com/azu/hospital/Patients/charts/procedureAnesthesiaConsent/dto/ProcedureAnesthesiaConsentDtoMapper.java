package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dto;


import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.entity.ProcedureAnesthesiaConsentChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcedureAnesthesiaConsentDtoMapper  implements BaseChartDtoMapperInterface {
  public ProcedureAnesthesiaConsentDto chartToDto(ProcedureAnesthesiaConsentChart chart){
    return Optional.ofNullable(chart)
            .map(c -> new ProcedureAnesthesiaConsentDto(
                    c.getId(),
                    c.getChartName(),
                    c.getDateOfVisit(),
                    c.getMrn(),
                    c.getProposedProcedures(),
                    c.getNotEatenOrDrankSinceTime(),
                    c.getNotEatenOrDrankSinceDate(),
                    c.getPatientSignature(),
                    c.getPatientSignatureDate(),
                    c.getWitnessSignature(),
                    c.getWitnessSignatureDate(),
                    c.getPhysicianSignature(),
                    c.getPhysicianSignatureDate(),
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
        ProcedureAnesthesiaConsentChart newChart = (ProcedureAnesthesiaConsentChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        ProcedureAnesthesiaConsentDto dto = new ProcedureAnesthesiaConsentDto();

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
        dto.setDateOfVisit(newChart.getDateOfVisit());
        dto.setMrn(newChart.getMrn());
        dto.setProposedProcedures(newChart.getProposedProcedures());
        dto.setNotEatenOrDrankSinceTime(newChart.getNotEatenOrDrankSinceTime());
        dto.setNotEatenOrDrankSinceDate(newChart.getNotEatenOrDrankSinceDate());
        dto.setPatientSignature(newChart.getPatientSignature());
        dto.setPatientSignatureDate(newChart.getPatientSignatureDate());
        dto.setWitnessSignature(newChart.getWitnessSignature());
        dto.setWitnessSignatureDate(newChart.getWitnessSignatureDate());
        dto.setPhysicianSignature(newChart.getPhysicianSignature());
        dto.setPhysicianSignatureDate(newChart.getPhysicianSignatureDate());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());

        return dto;
    }

    @Override
    public Class<ProcedureAnesthesiaConsentChart> getMappedClass() {
        return ProcedureAnesthesiaConsentChart.class;
    }
}
