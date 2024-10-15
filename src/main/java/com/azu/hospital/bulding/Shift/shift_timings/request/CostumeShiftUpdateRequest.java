package com.azu.hospital.bulding.Shift.shift_timings.request;

import com.azu.hospital.utils.enums.EnumRole;

import java.util.List;

public record CostumeShiftUpdateRequest(
        Long wardId,
        EnumRole role,
        List<TimingsArrangeUpdateRequest> listShiftTimings
) {
}
