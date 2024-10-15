package com.azu.hospital.Patients.charts.discharge.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.utils.enums.Discharge.ExitStatus;
import com.azu.hospital.utils.files.File;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class DischargeDto extends BaseChartsDto {
    private Long dischargeId;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Discharge Chart";

    private String dischargeDate;
    private String admissionDate;
    private String treatmentOnDischarge;
    private String surgicalMedicalProcedure;
    private String dischargeTime;
    private String recommendation;

    private Long createdBy;
    private Long LastModifiedBy;

    private Role role;

    private String title = "discharge";
    private String link = "discharge";

    public DischargeDto() {
    }

    public DischargeDto(
            Long dischargeId,
            String chartName,
            String dischargeDate,
            String dischargeTime,
            String treatmentOnDischarge,
            String recommendation,
            String surgicalMedicalProcedure,
            String admissionDate

    ) {
        this.dischargeId = dischargeId;
        this.chartName = chartName;
        this.dischargeDate = dischargeDate;
        this.dischargeTime = dischargeTime;
        this.treatmentOnDischarge = treatmentOnDischarge;
        this.recommendation = recommendation;
        this.surgicalMedicalProcedure = surgicalMedicalProcedure;
        this.admissionDate = admissionDate;

    }

    public DischargeDto(
            Long dischargeId,
            String chartName,
            String dischargeDate,
            String dischargeTime,
            String treatmentOnDischarge,
            String recommendation,
            String surgicalMedicalProcedure,
            String admissionDate,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.dischargeId = dischargeId;
        this.chartName = chartName;
        this.dischargeDate = dischargeDate;
        this.dischargeTime = dischargeTime;
        this.treatmentOnDischarge = treatmentOnDischarge;
        this.recommendation = recommendation;
        this.surgicalMedicalProcedure = surgicalMedicalProcedure;
        this.admissionDate = admissionDate;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }


}
