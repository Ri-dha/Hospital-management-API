package com.azu.hospital.bulding.Shift.shift_timings.dao.dao_timing_arrange;

import com.azu.hospital.bulding.Shift.shift_timings.entity.TimingsArrange;
import org.bouncycastle.asn1.x500.style.RFC4519Style;

import java.util.List;
import java.util.Optional;

public interface TimingsArrangeDao {

    void addListOfTimingsArrange(List<TimingsArrange> requests);

    void updateTimingsArrange(List<TimingsArrange> update);

    Optional<TimingsArrange> getTimingArrangeById(Long id);

    void deleteTimingsArrangeById(Long id);
}
