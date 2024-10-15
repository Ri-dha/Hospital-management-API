package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.MedsPerMDEntity;
import com.azu.hospital.Patients.charts.physical_assment.MedsPerMD;
import com.azu.hospital.Patients.charts.physical_assment.PatientLimitation;
import com.azu.hospital.Patients.charts.physical_assment.PatientPositions;
import com.azu.hospital.Patients.charts.physical_assment.ProcedureArea;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class NonSedatedPatientAssessmentDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Non Sedated Patient Assessment";
    private PatientPositions patientPositions;

    private PatientLimitation patientLimitation;

    private ProcedureArea procedureArea;

    private MedsPerMD medsPerMD;

    private Set<MedsPerMDEntity> medsPerMDList;

    private String allergy;

    private String medsPerMDListOther;

    private String nursesNotes;

    private String postOperativeDiagnosis;

    private String procedurePerformed;

    private String timeStart;

    private String timeFinish;

    private Long createdBy;
    private Long LastModifiedBy;

    private String title = "INTRA OPERATIVE PATIENT ASSESSMENT – NON SEDATED";
    private String link = "assessment-non-sedate";

    public NonSedatedPatientAssessmentDto() {
    }


    public NonSedatedPatientAssessmentDto(
            Long id,
            String chartName,
            PatientPositions patientPositions,
            PatientLimitation patientLimitation,
            ProcedureArea procedureArea,
            MedsPerMD medsPerMD,
            Set<MedsPerMDEntity> medsPerMDList,
            String medsPerMDListOther,
            String nursesNotes,
            String postOperativeDiagnosis,
            String procedurePerformed,
            String timeStart,
            String timeFinish,
            String allergy,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.patientPositions = patientPositions;
        this.patientLimitation = patientLimitation;
        this.procedureArea = procedureArea;
        this.medsPerMD = medsPerMD;
        this.medsPerMDList = medsPerMDList;
        this.medsPerMDListOther = medsPerMDListOther;
        this.nursesNotes = nursesNotes;
        this.postOperativeDiagnosis = postOperativeDiagnosis;
        this.procedurePerformed = procedurePerformed;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.allergy = allergy;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
