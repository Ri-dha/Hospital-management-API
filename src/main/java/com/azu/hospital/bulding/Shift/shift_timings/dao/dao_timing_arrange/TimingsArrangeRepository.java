package com.azu.hospital.bulding.Shift.shift_timings.dao.dao_timing_arrange;

import com.azu.hospital.bulding.Shift.shift_timings.entity.TimingsArrange;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface TimingsArrangeRepository extends JpaRepository<TimingsArrange, Long> {
}
