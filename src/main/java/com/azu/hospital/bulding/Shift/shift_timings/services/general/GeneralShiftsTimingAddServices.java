package com.azu.hospital.bulding.Shift.shift_timings.services.general;

import com.azu.hospital.bulding.Shift.shift_timings.dao.dao_timing_arrange.TimingsArrangeDao;
import com.azu.hospital.bulding.Shift.shift_timings.dao.general.GeneralShiftTimingDao;
import com.azu.hospital.bulding.Shift.shift_timings.entity.GeneralShiftsTiming;
import com.azu.hospital.bulding.Shift.shift_timings.entity.TimingsArrange;
import com.azu.hospital.bulding.Shift.shift_timings.request.GeneralShiftRegistrationsRequest;
import com.azu.hospital.bulding.Shift.shift_timings.request.TimingsArrangeRegistrationsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralShiftsTimingAddServices {

    private final GeneralShiftTimingDao generalShiftTimingDao;
    private final TimingsArrangeDao timingsArrangeDao;

    @Autowired
    public GeneralShiftsTimingAddServices(
            @Qualifier("GeneralShiftsTimingJpa") GeneralShiftTimingDao generalShiftTimingDao,
           @Qualifier("TimingsArrangeJpa") TimingsArrangeDao timingsArrangeDao
    ) {
        this.generalShiftTimingDao = generalShiftTimingDao;
        this.timingsArrangeDao = timingsArrangeDao;
    }


    public void addGeneralShiftTiming(GeneralShiftRegistrationsRequest request){

        GeneralShiftsTiming generalShiftsTiming = new GeneralShiftsTiming();

        List<TimingsArrange> timingsArrange = new ArrayList<>();
        for (TimingsArrangeRegistrationsRequest registrationsRequest : request.listShiftTimings()) {
            TimingsArrange arrange = new TimingsArrange();
            arrange.setStartFrom(registrationsRequest.startFrom());
            arrange.setToTime(registrationsRequest.toTime());
            arrange.setGeneralShiftsTiming(generalShiftsTiming);
            timingsArrange.add(arrange);
        }
        timingsArrangeDao.addListOfTimingsArrange(timingsArrange);
        generalShiftsTiming.setTimingsArranges(timingsArrange);
        generalShiftTimingDao.addGeneralShiftTiming(generalShiftsTiming);

    }
}
