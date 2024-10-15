package com.azu.hospital.Patients.charts.preOperativeHPChart.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.preOperativeHPChart.entity.PreOperativeHPChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreOperativeHPDtoMapper implements BaseChartDtoMapperInterface {
  public PreOperativeHPDto chartToDto(PreOperativeHPChart chart) {
    return Optional.ofNullable(chart)
            .map(c -> new PreOperativeHPDto(
                    c.getId(),
                    c.getChartName(),
                    c.getDiagnosisChiefComplaint(),
                    c.getPastMedicalHistory(),
                    c.getFamilyPhysician(),
                    c.getSurgicalHistory(),
                    c.getMedications(),
                    c.getHabit(),
                    c.getChronicDisease(),
                    c.getFamilyHistory(),
                    c.getGeneral(),
                    c.getHeadNeck(),
                    c.getChest(),
                    c.getHeart(),
                    c.getLungs(),
                    c.getAbdomen(),
                    c.getSkinOfExtremities(),
                    c.getImpression(),
                    c.getPatient().lastMedicalHistory().getDrugHistoryAllergy(),
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
        PreOperativeHPChart newChart = (PreOperativeHPChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        PreOperativeHPDto dto = new PreOperativeHPDto();

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
        dto.setDiagnosisChiefComplaint(newChart.getDiagnosisChiefComplaint());
        dto.setPastMedicalHistory(newChart.getPastMedicalHistory());
        dto.setFamilyPhysician(newChart.getFamilyPhysician());
        dto.setFamilyHistory(newChart.getFamilyHistory());
        dto.setSurgicalHistory(newChart.getSurgicalHistory());
        dto.setMedications(newChart.getMedications());
        dto.setChronicDisease(newChart.getChronicDisease());
        dto.setGeneral(newChart.getGeneral());
        dto.setHeadNeck(newChart.getHeadNeck());
        dto.setChest(newChart.getChest());
        dto.setHeart(newChart.getHeart());
        dto.setLungs(newChart.getLungs());
        dto.setAbdomen(newChart.getAbdomen());
        dto.setSkinOfExtremities(newChart.getSkinOfExtremities());
        dto.setImpression(newChart.getImpression());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());

        return dto;
    }

    @Override
    public Class<PreOperativeHPChart> getMappedClass() {
        return PreOperativeHPChart.class;
    }
}
