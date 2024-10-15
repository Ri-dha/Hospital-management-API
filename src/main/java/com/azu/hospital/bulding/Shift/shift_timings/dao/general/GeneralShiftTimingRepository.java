package com.azu.hospital.bulding.Shift.shift_timings.dao.general;

import com.azu.hospital.bulding.Shift.shift_timings.entity.GeneralShiftsTiming;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface GeneralShiftTimingRepository extends JpaRepository<GeneralShiftsTiming, Long> {
}
