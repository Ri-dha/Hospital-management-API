package com.azu.hospital.bulding.Shift.shift_timings.dto.general;

import com.azu.hospital.bulding.Shift.shift_timings.dto.timings_arrange.TimingsArrangeDtoMapper;
import com.azu.hospital.bulding.Shift.shift_timings.entity.GeneralShiftsTiming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GeneralShiftsTimingDtoMapper implements Function<GeneralShiftsTiming, GeneralShiftsTimingDto> {

    private final TimingsArrangeDtoMapper dtoMapper;

    @Autowired
    public GeneralShiftsTimingDtoMapper(TimingsArrangeDtoMapper dtoMapper) {
        this.dtoMapper = dtoMapper;
    }

    @Override
    public GeneralShiftsTimingDto apply(GeneralShiftsTiming generalShiftsTiming) {
        return new GeneralShiftsTimingDto(
                generalShiftsTiming.getGeneralId(),
                generalShiftsTiming.getTimingsArranges()
                        .stream()
                        .map(dtoMapper)
                        .collect(Collectors.toList()),
                generalShiftsTiming.getCreatedAt(),
                generalShiftsTiming.getUpdatedAt()
        );
    }
}
