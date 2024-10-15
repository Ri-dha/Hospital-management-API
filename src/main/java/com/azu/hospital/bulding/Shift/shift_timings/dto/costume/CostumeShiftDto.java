package com.azu.hospital.bulding.Shift.shift_timings.dto.costume;

import com.azu.hospital.bulding.Shift.shift_timings.dto.timings_arrange.TimingsArrangeDto;
import com.azu.hospital.utils.enums.EnumRole;

import java.time.Instant;
import java.util.List;

public record CostumeShiftDto(
        Long costumeId,
        Long wardId,
        String wardName,
        EnumRole role,
       List<TimingsArrangeDto> shiftTimes,
        Instant createdAt,
        Instant updatedAt
) {

}
