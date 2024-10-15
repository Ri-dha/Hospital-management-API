package com.azu.hospital.bulding.Shift.shift_timings.request;

import com.azu.hospital.utils.enums.EnumRole;

import java.util.List;

public record CostumeShiftRegistrationsRequest(
        Long wardId,
        EnumRole role,
        List<TimingsArrangeRegistrationsRequest> listShiftTimings
) {
}
