package com.azu.hospital.bulding.Shift.shift_timings.request;

import java.sql.Time;

public record TimingsArrangeUpdateRequest(
        long id,
        Time startFrom,
        Time toTime
) {
}
