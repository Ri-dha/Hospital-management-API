package com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assment.*;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities.ProcedureTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class SedatedPatientAssessmentDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Sedated Patient Assessment Chart";
    private String medications;

    private String positiveProblems;

    private String preOpMeds;

    private String asa;

    private PreProcedure preProcedure;

    private PreAnesthesiaState preAnesthesiaState;

    private PatientSafety patientSafety;

    private EyeCare eyeCare;

    private AnestheticTechnique anestheticTechnique;

    private MonitorsEquipment monitorsEquipment;

    private AirwayManagement airwayManagement;

    private String anesthesiaStartTime;

    private String anesthesiaEndTime;

    private String procedureStartTime;

    private String procedureEndTime;

    private Long createdBy;
    private Long LastModifiedBy;


    private String title = "INTRA OPERATIVE PATIENT ASSESSMENT - SEDATED";
    private String link = "assessment-sedate";

    public SedatedPatientAssessmentDto() {
    }

    public SedatedPatientAssessmentDto(
            Long id,
            String chartName,
            String medications,
            String positiveProblems,
            String preOpMeds,
            String asa,
            PreProcedure preProcedure,
            PreAnesthesiaState preAnesthesiaState,
            PatientSafety patientSafety,
            EyeCare eyeCare,
            AnestheticTechnique anestheticTechnique,
            MonitorsEquipment monitorsEquipment,
            AirwayManagement airwayManagement,
            String anesthesiaStartTime,
            String anesthesiaEndTime,
            String procedureStartTime,
            String procedureEndTime,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.medications = medications;
        this.positiveProblems = positiveProblems;
        this.preOpMeds = preOpMeds;
        this.asa = asa;
        this.preProcedure = preProcedure;
        this.preAnesthesiaState = preAnesthesiaState;
        this.patientSafety = patientSafety;
        this.eyeCare = eyeCare;
        this.anestheticTechnique = anestheticTechnique;
        this.monitorsEquipment = monitorsEquipment;
        this.airwayManagement = airwayManagement;
        this.anesthesiaStartTime = anesthesiaStartTime;
        this.anesthesiaEndTime = anesthesiaEndTime;
        this.procedureStartTime = procedureStartTime;
        this.procedureEndTime = procedureEndTime;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
