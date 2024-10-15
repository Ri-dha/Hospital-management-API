package com.azu.hospital.bulding.Shift.shift_timings.dao.general;

import com.azu.hospital.bulding.Shift.shift_timings.entity.GeneralShiftsTiming;

import java.util.List;
import java.util.Optional;

public interface GeneralShiftTimingDao {

    void addGeneralShiftTiming(GeneralShiftsTiming request);

    void updateGeneralShiftsTiming(GeneralShiftsTiming update);

    Optional<GeneralShiftsTiming> getGeneralShiftsTimingById(Long id);

    List<GeneralShiftsTiming> getAllGeneralShiftsTiming();


}
