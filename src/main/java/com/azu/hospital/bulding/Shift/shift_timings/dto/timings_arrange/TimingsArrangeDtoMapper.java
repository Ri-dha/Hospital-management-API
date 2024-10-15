package com.azu.hospital.bulding.Shift.shift_timings.dto.timings_arrange;

import com.azu.hospital.bulding.Shift.shift_timings.dto.timings_arrange.TimingsArrangeDto;
import com.azu.hospital.bulding.Shift.shift_timings.entity.TimingsArrange;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TimingsArrangeDtoMapper implements Function<TimingsArrange, TimingsArrangeDto> {

    @Override
    public TimingsArrangeDto apply(TimingsArrange timingsArrange) {
        return new TimingsArrangeDto(
                timingsArrange.getId(),
                timingsArrange.getStartFrom(),
                timingsArrange.getToTime()
        );
    }
}
