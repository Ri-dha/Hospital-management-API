package com.azu.hospital.bulding.Shift.shift_timings.dto.timings_arrange;

import java.sql.Time;

public record TimingsArrangeDto(
        long id,
        Time startFrom,
        Time toTime
) {
}
