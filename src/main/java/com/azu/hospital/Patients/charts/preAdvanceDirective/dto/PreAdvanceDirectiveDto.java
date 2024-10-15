package com.azu.hospital.Patients.charts.preAdvanceDirective.dto;


import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PreAdvanceDirectiveDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Pre Advance Directive Chart";
    private String option;

    private String printName;

    private String signature;

    private String date;

    private String preOpNurseSignature;

    private String preOpNurseDate;

    private String preOpNurseTime;

    private Long createdBy;
    private Long LastModifiedBy;


    private String title = "PRE OPERATIVE ADVANCE DIRECTIVE";
    private String link = "directive";

    public PreAdvanceDirectiveDto() {
    }

    public PreAdvanceDirectiveDto(
            Long id,
            String chartName,
            String option,
            String printName,
            String signature,
            String date,
            String preOpNurseSignature,
            String preOpNurseDate,
            String preOpNurseTime,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.option = option;
        this.printName = printName;
        this.signature = signature;
        this.date = date;
        this.preOpNurseSignature = preOpNurseSignature;
        this.preOpNurseDate = preOpNurseDate;
        this.preOpNurseTime = preOpNurseTime;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
