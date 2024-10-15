package com.azu.hospital.Patients.charts.painManagment.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assessment_enm.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PainManagementDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Pain Management Chart";

    private PainFeel painFeel;

    private String painFeelRadiating;

    private Boolean painGoAnywhereElse;

    private String majorLifeChanges;

    private PainStatus painStatus;

    private String painStarted;

    private PainStartDetails painStartDetails;

    private PainWorseEnum painWorse;

    private String painWorseOther;

    private PainWorseEnum painBetter;

    private String painBetterOther;

    private DayTimes timePainGetWorse;

    private String timePainGetWorseOther;

    private String painDescribes;

    private Boolean painInterruptSleep;

    private Map<TreatmentType, Boolean> triedTreatments;

    private Map<DiagnosticType, Boolean> lastYearTest;

    private Long createdBy;
    private Long LastModifiedBy;

    private String otherLastYearTest;
    private String title = "Pain";
    private String link = "pain";

    public PainManagementDto() {
    }

    public PainManagementDto(
            Long id,
            String chartName,
            PainFeel painFeel,
            String painFeelRadiating,
            Boolean painGoAnywhereElse,
            String majorLifeChanges,
            PainStatus painStatus,
            String painStarted,
            PainStartDetails painStartDetails,
            PainWorseEnum painWorse,
            String painWorseOther,
            PainWorseEnum painBetter,
            String painBetterOther,
            DayTimes timePainGetWorse,
            String timePainGetWorseOther,
            String painDescribes,
            Boolean painInterruptSleep,
            Map<TreatmentType, Boolean> triedTreatments,
            Map<DiagnosticType, Boolean> lastYearTest,
            String otherLastYearTest,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.painFeel = painFeel;
        this.painFeelRadiating = painFeelRadiating;
        this.painGoAnywhereElse = painGoAnywhereElse;
        this.majorLifeChanges = majorLifeChanges;
        this.painStatus = painStatus;
        this.painStarted = painStarted;
        this.painStartDetails = painStartDetails;
        this.painWorse = painWorse;
        this.painWorseOther = painWorseOther;
        this.painBetter = painBetter;
        this.painBetterOther = painBetterOther;
        this.timePainGetWorse = timePainGetWorse;
        this.timePainGetWorseOther = timePainGetWorseOther;
        this.painDescribes = painDescribes;
        this.painInterruptSleep = painInterruptSleep;
        this.triedTreatments = triedTreatments;
        this.lastYearTest = lastYearTest;
        this.otherLastYearTest = otherLastYearTest;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
