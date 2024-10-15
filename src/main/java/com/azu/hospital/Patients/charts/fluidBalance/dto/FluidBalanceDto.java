package com.azu.hospital.Patients.charts.fluidBalance.dto;


import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidIntake;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidOutput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class FluidBalanceDto extends BaseChartsDto {
    private Long fluidChartId;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Fluid Balance Chart";

    private String dateFrom;

    private String dateTo;

    private BigDecimal ml24InputHour;

    private BigDecimal fluidBalance24InputHour;

    private List<FluidIntake> inputs;

    private List<FluidOutput> outputs;

    private String candidate;

    private Long createdBy;
    private Long LastModifiedBy;

    private String title = "Fluid Balance";
    private String link = "fluid-balance";

    public FluidBalanceDto() {
    }

    public FluidBalanceDto(
            Long fluidChartId,
            String chartName,
            String dateFrom,
            String dateTo,
            BigDecimal ml24InputHour,
            BigDecimal fluidBalance24InputHour,
            List<FluidIntake> inputs,
            List<FluidOutput> outputs,
            String candidate,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.fluidChartId = fluidChartId;
        this.chartName = chartName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.ml24InputHour = ml24InputHour;
        this.fluidBalance24InputHour = fluidBalance24InputHour;
        this.inputs = inputs;
        this.outputs = outputs;
        this.candidate = candidate;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
