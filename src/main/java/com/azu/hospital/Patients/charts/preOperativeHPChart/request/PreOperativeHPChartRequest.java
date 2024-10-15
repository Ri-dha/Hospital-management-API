package com.azu.hospital.Patients.charts.preOperativeHPChart.request;



import com.azu.hospital.Patients.charts.physical_assessment_enm.HealthCondition;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedicalCondition;
import com.azu.hospital.Patients.charts.physical_assment.Habit;

import java.util.Map;

public record PreOperativeHPChartRequest(
        Long id,
        String diagnosisChiefComplaint,
        String pastMedicalHistory,
        String familyPhysician,
        String surgicalHistory,
        String medications,
        Map<Habit, Boolean> hobbies,
        Map<MedicalCondition, Boolean> chronicDisease,
        Map<HealthCondition, Boolean> familyHistory,
        String general,
        String headNeck,
        String chest,
        String heart,
        String lungs,
        String abdomen,
        String skinOfExtremities,
        String extremitiesSkin,
        String impression

        // TODO: 9/11/2023 discussions for more accuracy
) {
}
