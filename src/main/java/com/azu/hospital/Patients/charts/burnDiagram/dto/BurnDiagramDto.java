package com.azu.hospital.Patients.charts.burnDiagram.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnBodyDiagram;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class BurnDiagramDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Burn Diagram Chart";

    private String dateOfBurn;

    private String dateOfAdmission;

    private String dateOfTbsaEstimationPreliminary;

    private String preliminaryDoctorName;

    private String dateOfTbsaEstimationFinal;

    private String finalDoctorName;

    private Float superficialDermal;

    private Float midDermal;

    private Float deepDermal;

    private Float midDermalFluidLoos;

    private Float deepDermalFluidLoss;

    private Float finalTbsa;

    private Float totalFluidLoos;

    private BurnBodyDiagram burnBodyDiagram;

    private String title = "Burn Diagram";
    private String link = "burn-diagram";

    private Long createdBy;
    private Long lastModifiedBy;

    public BurnDiagramDto() {
    }

    public BurnDiagramDto(
            Long id,
            String chartName,
            String dateOfBurn,
            String dateOfAdmission,
            String dateOfTbsaEstimationPreliminary,
            String preliminaryDoctorName,
            String dateOfTbsaEstimationFinal,
            String finalDoctorName,
            Float superficialDermal,
            Float midDermal,
            Float deepDermal,
            Float midDermalFluidLoos,
            Float deepDermalFluidLoss,
            Float finalTbsa,
            Float totalFluidLoos,
            BurnBodyDiagram burnBodyDiagram,
            Long createdBy,
            Long lastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.dateOfBurn = dateOfBurn;
        this.dateOfAdmission = dateOfAdmission;
        this.dateOfTbsaEstimationPreliminary = dateOfTbsaEstimationPreliminary;
        this.preliminaryDoctorName = preliminaryDoctorName;
        this.dateOfTbsaEstimationFinal = dateOfTbsaEstimationFinal;
        this.finalDoctorName = finalDoctorName;
        this.superficialDermal = superficialDermal;
        this.midDermal = midDermal;
        this.deepDermal = deepDermal;
        this.midDermalFluidLoos = midDermalFluidLoos;
        this.deepDermalFluidLoss = deepDermalFluidLoss;
        this.finalTbsa = finalTbsa;
        this.totalFluidLoos = totalFluidLoos;
        this.burnBodyDiagram = burnBodyDiagram;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }






}
