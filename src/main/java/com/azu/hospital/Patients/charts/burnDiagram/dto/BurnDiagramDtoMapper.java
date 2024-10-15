package com.azu.hospital.Patients.charts.burnDiagram.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnDiagramChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BurnDiagramDtoMapper implements BaseChartDtoMapperInterface {
    public BurnDiagramDto chartToDto(BurnDiagramChart chart) {
        return Optional.ofNullable(chart)
                .map(c -> new BurnDiagramDto(
                                c.getId(),
                                c.getChartName(),
                                c.getDateOfBurn(),
                                c.getDateOfAdmission(),
                                c.getDateOfTbsaEstimationPreliminary(),
                                c.getPreliminaryDoctorName(),
                                c.getDateOfTbsaEstimationFinal(),
                                c.getFinalDoctorName(),
                                c.getSuperficialDermal(),
                                c.getMidDermal(),
                                c.getDeepDermal(),
                                c.getMidDermalFluidLoos(),
                                c.getDeepDermalFluidLoss(),
                                c.getFinalTbsa(),
                                c.getTotalFluidLoos(),
                                c.getBurnBodyDiagram(),
                                c.getCreatedBy(),
                                c.getLastModifiedBy()
                        )
                ).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "There Is No Data To Returning"
                        )
                );
    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        BurnDiagramChart newChart = (BurnDiagramChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        BurnDiagramDto diagramDto = new BurnDiagramDto();
        diagramDto.setId(newChart.getId());
        diagramDto.setPatientId(newChart.getPatient().getId());
        diagramDto.setDoctorId(newChart.getPatient().getDoctorSpecials().getDoctor().getId());
        diagramDto.setDoctorName(newChart.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial());
        diagramDto.setPatientName(newChart.getPatient().getPatientData().getFullName());
        diagramDto.setPatientAge(newChart.getPatient().getPatientDate().getAge());
        diagramDto.setPatientGender(newChart.getPatient().getPatientData().getGender().name());
        diagramDto.setWardName(newChart.getPatient().getWard().getName());
        diagramDto.setBedNumber(newChart.getPatient().getBed().getBedNumber());
        diagramDto.setChartName(newChart.getChartName());
        diagramDto.setDateOfBurn(newChart.getDateOfBurn());
        diagramDto.setDateOfAdmission(newChart.getDateOfAdmission());
        diagramDto.setDateOfTbsaEstimationPreliminary(newChart.getDateOfTbsaEstimationPreliminary());
        diagramDto.setPreliminaryDoctorName(newChart.getPreliminaryDoctorName());
        diagramDto.setDateOfTbsaEstimationFinal(newChart.getDateOfTbsaEstimationFinal());
        diagramDto.setFinalDoctorName(newChart.getFinalDoctorName());
        diagramDto.setSuperficialDermal(newChart.getSuperficialDermal());
        diagramDto.setMidDermal(newChart.getMidDermal());
        diagramDto.setDeepDermal(newChart.getDeepDermal());
        diagramDto.setMidDermalFluidLoos(newChart.getMidDermalFluidLoos());
        diagramDto.setDeepDermalFluidLoss(newChart.getDeepDermalFluidLoss());
        diagramDto.setFinalTbsa(newChart.getFinalTbsa());
        diagramDto.setTotalFluidLoos(newChart.getTotalFluidLoos());
        diagramDto.setBurnBodyDiagram(newChart.getBurnBodyDiagram());
        diagramDto.setCreatedBy(newChart.getCreatedBy());
        diagramDto.setLastModifiedBy(newChart.getLastModifiedBy());

        return diagramDto;
    }

    @Override
    public Class<BurnDiagramChart> getMappedClass() {
        return BurnDiagramChart.class;
    }
}
