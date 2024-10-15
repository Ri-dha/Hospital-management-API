package com.azu.hospital.Patients.charts.patientConsentAnesthesia.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.entity.PatientConsentAnesthesiaChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientConsentAnesthesiaDtoMapper implements BaseChartDtoMapperInterface {
  public PatientConsentAnesthesiaDto chartToDto(PatientConsentAnesthesiaChart chart){
    return Optional.ofNullable(chart)
            .map(c -> new PatientConsentAnesthesiaDto(
                    c.getId(),
                    c.getChartName(),
                    c.getAnesthesiaType(),
                    c.getPatientSignature(),
                    c.getPatientSignatureDate(),
                    c.getPatientSignatureTime(),
                    c.getWitnessSignature(),
                    c.getWitnessSignatureDate(),
                    c.getWitnessSignatureTime(),
                    c.getAnesthesiaProviderSignature(),
                    c.getAnesthesiaProviderSignatureDate(),
                    c.getAnesthesiaProviderSignatureTime(),
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
        PatientConsentAnesthesiaChart newChart = (PatientConsentAnesthesiaChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        PatientConsentAnesthesiaDto dto = new PatientConsentAnesthesiaDto();
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
        dto.setAnesthesiaType(newChart.getAnesthesiaType());
        dto.setPatientSignature(newChart.getPatientSignature());
        dto.setPatientSignatureDate(newChart.getPatientSignatureDate());
        dto.setPatientSignatureTime(newChart.getPatientSignatureTime());
        dto.setWitnessSignature(newChart.getWitnessSignature());
        dto.setWitnessSignatureDate(newChart.getWitnessSignatureDate());
        dto.setWitnessSignatureTime(newChart.getWitnessSignatureTime());
        dto.setAnesthesiaProviderSignature(newChart.getAnesthesiaProviderSignature());
        dto.setAnesthesiaProviderSignatureDate(newChart.getAnesthesiaProviderSignatureDate());
        dto.setAnesthesiaProviderSignatureTime(newChart.getAnesthesiaProviderSignatureTime());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;
    }

    @Override
    public Class<PatientConsentAnesthesiaChart> getMappedClass() {
        return PatientConsentAnesthesiaChart.class;
    }
}
