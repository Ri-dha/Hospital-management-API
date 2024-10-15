package com.azu.hospital.bulding.Shift.shift_timings.request;

import java.sql.Time;

public record TimingsArrangeRegistrationsRequest(
        Time startFrom,
        Time toTime
) {
}
