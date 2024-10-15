package com.azu.hospital.bulding.Shift.arrange_shifts.dto;

import com.azu.hospital.utils.enums.EnumRole;
import com.azu.hospital.utils.enums.Gender;

import java.sql.Time;

public record UserShiftsTableDto(
        long id,
        long wardId,
        String wardName,
        long shiftId,
        Time shiftTime,
        long userId,
        String userName,
        EnumRole role,
        String userImage,
        String specialists,
        Gender gender
) {
}
