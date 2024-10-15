package com.azu.hospital.Patients.charts.nursingObservation.request;

import com.azu.hospital.utils.enums.AdministrationRoute;

import java.util.List;

public record NursingObservationRequest(
        String note,
        List<ObservationTimeRequest> observationTimes

) {
}
