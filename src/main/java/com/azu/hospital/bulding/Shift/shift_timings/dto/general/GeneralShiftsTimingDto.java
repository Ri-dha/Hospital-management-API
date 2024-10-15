package com.azu.hospital.bulding.Shift.shift_timings.dto.general;

import com.azu.hospital.bulding.Shift.shift_timings.dto.timings_arrange.TimingsArrangeDto;

import java.time.Instant;
import java.util.List;

public record GeneralShiftsTimingDto(
        Long generalId,
        List<TimingsArrangeDto> timingsArrange,
        Instant createdAt,
        Instant updatedAt
) {
}
