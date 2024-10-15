package com.azu.hospital.bulding.Shift.dto;

import com.azu.hospital.bulding.Shift.entity.Shift;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ShiftDtoMapper implements Function<Shift, ShiftDto> {
    @Override
    public ShiftDto apply(Shift shift) {
        return new ShiftDto(
                shift.getId(),
                shift.getName(),
                shift.getFromTime(),
                shift.getToTime(),
                shift.getCreatedAt(),
                shift.getUpdatedAt() == null ? null : shift.getUpdatedAt()
        );
    }
}
