package com.azu.hospital.patient_expances.request;

import com.azu.hospital.patient_expances.entity.PatientExpanse;

import java.util.List;

public record PatientExpanseListUpdate (
    List<PatientExpanseUpdate> requestList
    )
{
}
