package com.azu.hospital.bulding.Shift.shift_timings.dto.costume;

import com.azu.hospital.bulding.Shift.shift_timings.dto.timings_arrange.TimingsArrangeDtoMapper;
import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CostumeShiftDtoMapper implements Function<CostumeShift, CostumeShiftDto> {
    private final TimingsArrangeDtoMapper timingsArrangeDtoMapper;

    @Autowired
    public CostumeShiftDtoMapper(TimingsArrangeDtoMapper timingsArrangeDtoMapper) {
        this.timingsArrangeDtoMapper = timingsArrangeDtoMapper;
    }

    @Override
    public CostumeShiftDto apply(CostumeShift costumeShift) {
        return new CostumeShiftDto(
                costumeShift.getCostumeId(),
                costumeShift.getWard().getWardId(),
                costumeShift.getWard().getName(),
                costumeShift.getRole(),
                costumeShift.getCostumeListTim()
                        .stream()
                        .map(timingsArrangeDtoMapper)
                        .collect(Collectors.toList()),
                costumeShift.getCreatedAt(),
                costumeShift.getUpdatedAt()
        );
    }
}
