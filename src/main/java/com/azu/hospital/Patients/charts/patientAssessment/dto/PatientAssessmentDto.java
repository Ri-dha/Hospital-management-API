package com.azu.hospital.Patients.charts.patientAssessment.dto;


import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assessment_enm.PreProcedureChecklist;
import com.azu.hospital.Patients.charts.physical_assessment_enm.SkinCondition;
import com.azu.hospital.Patients.charts.physical_assment.IVSite;
import com.azu.hospital.Patients.charts.physical_assment.PatientLOC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PatientAssessmentDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Patient Assessment Chart";
    private String diagnosis;
    private String procedures;
    private SkinCondition skinCondition;
    private Map<String, Boolean> physicalLimitations;
    private PatientLOC loc;
    private IVSite ivSite;
    private Map<PreProcedureChecklist, Boolean> preProcedureChecklist;

    private Long createdBy;
    private Long LastModifiedBy;

    private String title = "PRE OPERATIVE PATIENT ASSESSMENT";
    private String link =  "assessment";



    public PatientAssessmentDto(
            Long id,
            String chartName,
            String diagnosis,
            String procedures,
            SkinCondition skinCondition,
            Map<String, Boolean> physicalLimitations,
            PatientLOC loc,
            IVSite ivSite,
            Map<PreProcedureChecklist, Boolean> preProcedureChecklist,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.diagnosis = diagnosis;
        this.procedures = procedures;
        this.skinCondition = skinCondition;
        this.physicalLimitations = physicalLimitations;
        this.loc = loc;
        this.ivSite = ivSite;
        this.preProcedureChecklist = preProcedureChecklist;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }

    public PatientAssessmentDto() {

    }
}
