package com.azu.hospital.bulding.Shift.shift_timings.services.general;

import com.azu.hospital.bulding.Shift.shift_timings.dao.dao_timing_arrange.TimingsArrangeDao;
import com.azu.hospital.bulding.Shift.shift_timings.dao.general.GeneralShiftTimingDao;
import com.azu.hospital.bulding.Shift.shift_timings.entity.GeneralShiftsTiming;
import com.azu.hospital.bulding.Shift.shift_timings.entity.TimingsArrange;
import com.azu.hospital.bulding.Shift.shift_timings.request.GeneralShiftUpdateRequest;
import com.azu.hospital.bulding.Shift.shift_timings.request.TimingsArrangeUpdateRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralShiftsTimingUpdateServices {

    private final GeneralShiftTimingDao generalShiftTimingDao;
    private final TimingsArrangeDao timingsArrangeDao;

    @Autowired
    public GeneralShiftsTimingUpdateServices(@Qualifier("GeneralShiftsTimingJpa") GeneralShiftTimingDao generalShiftTimingDao,
                                             @Qualifier("TimingsArrangeJpa") TimingsArrangeDao timingsArrangeDao) {
        this.generalShiftTimingDao = generalShiftTimingDao;
        this.timingsArrangeDao = timingsArrangeDao;
    }

    public void updateGeneralShiftTiming(Long id, GeneralShiftUpdateRequest update){
        GeneralShiftsTiming generalShiftsTiming = generalShiftTimingDao.getGeneralShiftsTimingById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "There is no general shift sitting"
                        )
                );

        boolean changes = false;
        if (!update.listShiftTimings().isEmpty()){
            for (TimingsArrangeUpdateRequest request : update.listShiftTimings()){
                TimingsArrange timingsArrange = timingsArrangeDao.getTimingArrangeById(request.id())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Time pieces not found"
                                )
                        );
                if (request.startFrom() != null && request.startFrom() != timingsArrange.getStartFrom()){
                    timingsArrange.setStartFrom(request.startFrom());
                    changes = true;
                }if (request.toTime() != null && request.toTime() != timingsArrange.getToTime()){
                    timingsArrange.setToTime(request.toTime());
                    changes = true;
                }if (!changes) throw new ResourceNotFoundException("There is no changes");

                timingsArrangeDao.updateTimingsArrange(List.of(timingsArrange));
            }
        }
        generalShiftTimingDao.updateGeneralShiftsTiming(generalShiftsTiming);
    }


    // if the user updates from shift 4 to 2 0r three shifts, he can delete the rest shift


    public void deleteTimingsArrangeById(Long id) {
        TimingsArrange timingsArrange = timingsArrangeDao.getTimingArrangeById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Time pieces not found"
                        )
                );

        timingsArrangeDao.deleteTimingsArrangeById(timingsArrange.getId());
    }
}
