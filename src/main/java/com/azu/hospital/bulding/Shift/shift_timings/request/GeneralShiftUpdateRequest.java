package com.azu.hospital.bulding.Shift.shift_timings.request;

import java.util.List;

public record GeneralShiftUpdateRequest(
        List<TimingsArrangeUpdateRequest> listShiftTimings
) {
}
