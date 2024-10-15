package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.request;

import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientExperiencingEnum;

public record PatientExperiencingRequest(
        PatientExperiencingEnum experience,
        Boolean isChecked,
        String note
) {
}
