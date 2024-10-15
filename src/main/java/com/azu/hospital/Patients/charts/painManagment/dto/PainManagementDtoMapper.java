package com.azu.hospital.Patients.charts.painManagment.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.painManagment.entity.PainManagementChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PainManagementDtoMapper implements BaseChartDtoMapperInterface {
    public PainManagementDto chartToDto(PainManagementChart chart) {
        return Optional.ofNullable(chart)
                .map(c -> new PainManagementDto(
                        c.getId(),
                        c.getChartName(),
                        c.getPainFeel(),
                        c.getPainFeelRadiating(),
                        c.getPainGoAnywhereElse(),
                        c.getMajorLifeChanges(),
                        c.getPainStatus(),
                        c.getPainStarted(),
                        c.getPainStartDetails(),
                        c.getPainWorse(),
                        c.getPainWorseOther(),
                        c.getPainBetter(),
                        c.getPainBetterOther(),
                        c.getTimePainGetWorse(),
                        c.getTimePainGetWorseOther(),
                        c.getPainDescribes(),
                        c.getPainInterruptSleep(),
                        c.getTriedTreatments(),
                        c.getLastYearTest(),
                        c.getOtherLastYearTest(),
                        c.getCreatedBy(),
                        c.getLastModifiedBy()
                ))
                .orElseThrow(
                        () -> new ResourceNotFoundException("There is no data for returning")
                );
    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        PainManagementChart newChart = (PainManagementChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        PainManagementDto dto = new PainManagementDto();
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
        dto.setPainFeel(newChart.getPainFeel());
        dto.setPainFeelRadiating(newChart.getPainFeelRadiating());
        dto.setPainGoAnywhereElse(newChart.getPainGoAnywhereElse());
        dto.setMajorLifeChanges(newChart.getMajorLifeChanges());
        dto.setPainStatus(newChart.getPainStatus());
        dto.setPainStarted(newChart.getPainStarted());
        dto.setPainStartDetails(newChart.getPainStartDetails());
        dto.setPainWorse(newChart.getPainWorse());
        dto.setPainWorseOther(newChart.getPainWorseOther());
        dto.setPainBetter(newChart.getPainBetter());
        dto.setPainBetterOther(newChart.getPainBetterOther());
        dto.setTimePainGetWorse(newChart.getTimePainGetWorse());
        dto.setTimePainGetWorseOther(newChart.getTimePainGetWorseOther());
        dto.setPainDescribes(newChart.getPainDescribes());
        dto.setPainInterruptSleep(newChart.getPainInterruptSleep());
        dto.setTriedTreatments(newChart.getTriedTreatments());
        dto.setLastYearTest(newChart.getLastYearTest());
        dto.setOtherLastYearTest(newChart.getOtherLastYearTest());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;
    }

    @Override
    public Class<PainManagementChart> getMappedClass() {
        return PainManagementChart.class;
    }
}
