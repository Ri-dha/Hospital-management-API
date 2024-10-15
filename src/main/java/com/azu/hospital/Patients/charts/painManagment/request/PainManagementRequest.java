package com.azu.hospital.Patients.charts.painManagment.request;

import com.azu.hospital.Patients.charts.physical_assessment_enm.*;

import java.time.LocalDate;
import java.util.Map;

public record PainManagementRequest(
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
        Map<TreatmentType, Boolean>triedTreatments,
        Map<DiagnosticType, Boolean> lastYearTest,
        String otherLastYearTest
) {
}
