package com.azu.hospital.Patients.charts.nursingObservation.request;

import com.azu.hospital.utils.enums.AdministrationRoute;

public record ObservationTimeRequest(

        Long requestId,
        String time,
        String timeStatus,
        String roa

) {
}
