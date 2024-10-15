package com.azu.hospital.bulding.Shift.shift_timings.services.costume;

import com.azu.hospital.bulding.Shift.shift_timings.dao.dao_costume.CostumeShiftDao;
import com.azu.hospital.bulding.Shift.shift_timings.dao.dao_timing_arrange.TimingsArrangeDao;
import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import com.azu.hospital.bulding.Shift.shift_timings.entity.TimingsArrange;
import com.azu.hospital.bulding.Shift.shift_timings.request.CostumeShiftRegistrationsRequest;
import com.azu.hospital.bulding.Shift.shift_timings.request.TimingsArrangeRegistrationsRequest;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CostumeShiftAddServices {

    private final CostumeShiftDao costumeShiftDao;
    private final WardDao wardDao;
    private final TimingsArrangeDao timingsArrangeDao;

    @Autowired
    public CostumeShiftAddServices(CostumeShiftDao costumeShiftDao, WardDao wardDao, TimingsArrangeDao timingsArrangeDao) {
        this.costumeShiftDao = costumeShiftDao;
        this.wardDao = wardDao;
        this.timingsArrangeDao = timingsArrangeDao;
    }


    @Transactional
    public void addCostumeShift(CostumeShiftRegistrationsRequest request){

        Ward ward = wardDao.findWardById(request.wardId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Ward not found"
                        )
                );

        checkForCreatNewCostumeShiftAndExistShift(request);

        CostumeShift costumeShift = new CostumeShift();
        costumeShift.setRole(request.role());
        costumeShift.setWard(ward);

       List<TimingsArrange> timingsArranges = new ArrayList<>();
        for (TimingsArrangeRegistrationsRequest arrangeRequest : request.listShiftTimings()){
            TimingsArrange arrange = new TimingsArrange();
            arrange.setStartFrom(arrangeRequest.startFrom());
            arrange.setToTime(arrangeRequest.toTime());
            arrange.setCostumeShift(costumeShift);
            timingsArranges.add(arrange);
        }
        timingsArrangeDao.addListOfTimingsArrange(timingsArranges);
        costumeShift.setCostumeListTim(timingsArranges);
        costumeShiftDao.addNewCostumeShift(costumeShift);
    }

    private void checkForCreatNewCostumeShiftAndExistShift(CostumeShiftRegistrationsRequest request) {
        List<CostumeShift> costumeShifts = costumeShiftDao.getAllCostumeShift();
        for (CostumeShift shift : costumeShifts){
            if (shift.getRole() == request.role() && Objects.equals(shift.getWard().getWardId(), request.wardId())){
                throw new DuplicateResourceException(
                        "This Shift already exist you can update it"
                );
            }
        }
    }
}
