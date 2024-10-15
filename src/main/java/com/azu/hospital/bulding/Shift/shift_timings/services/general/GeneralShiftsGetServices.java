package com.azu.hospital.bulding.Shift.shift_timings.services.general;

import com.azu.hospital.bulding.Shift.shift_timings.dao.general.GeneralShiftTimingDao;
import com.azu.hospital.bulding.Shift.shift_timings.dto.general.GeneralShiftsTimingDto;
import com.azu.hospital.bulding.Shift.shift_timings.dto.general.GeneralShiftsTimingDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralShiftsGetServices {

    private final GeneralShiftTimingDao generalShiftTimingDao;
    private final GeneralShiftsTimingDtoMapper mapper;

    @Autowired
    public GeneralShiftsGetServices(
            @Qualifier("GeneralShiftsTimingJpa") GeneralShiftTimingDao generalShiftTimingDao,
            GeneralShiftsTimingDtoMapper mapper
    ) {
        this.generalShiftTimingDao = generalShiftTimingDao;
        this.mapper = mapper;
    }

    public List<GeneralShiftsTimingDto> getAllGeneralShiftsTimelines(){
        return generalShiftTimingDao.getAllGeneralShiftsTiming()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
