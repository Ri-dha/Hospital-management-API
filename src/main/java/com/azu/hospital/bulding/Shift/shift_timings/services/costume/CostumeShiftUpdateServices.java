package com.azu.hospital.bulding.Shift.shift_timings.services.costume;

import com.azu.hospital.bulding.Shift.shift_timings.dao.dao_costume.CostumeShiftDao;
import com.azu.hospital.bulding.Shift.shift_timings.dao.dao_timing_arrange.TimingsArrangeDao;
import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import com.azu.hospital.bulding.Shift.shift_timings.entity.TimingsArrange;
import com.azu.hospital.bulding.Shift.shift_timings.request.CostumeShiftUpdateRequest;
import com.azu.hospital.bulding.Shift.shift_timings.request.TimingsArrangeUpdateRequest;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CostumeShiftUpdateServices {

    private final CostumeShiftDao costumeShiftDao;
    private final WardDao wardDao;
    private final TimingsArrangeDao timingsArrangeDao;

    @Autowired
    public CostumeShiftUpdateServices(CostumeShiftDao costumeShiftDao, WardDao wardDao, TimingsArrangeDao timingsArrangeDao) {
        this.costumeShiftDao = costumeShiftDao;
        this.wardDao = wardDao;
        this.timingsArrangeDao = timingsArrangeDao;
    }

    // TODO: 1/31/2024 error
    public void updateCostumeShift(Long id, CostumeShiftUpdateRequest request){
        CostumeShift costumeShift = costumeShiftDao.getCostumeShiftById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "No such shift"
                        )
                );
        boolean changes = false;
        if (request.wardId() != null){
            changes = isChangesInWardOfCostumeShift(request, costumeShift);

        }if (request.role() != null){
            costumeShift.setRole(request.role());
            changes = true;
       }
//        if (!request.listShiftTimings().isEmpty()){
//            updateTimingsArrangeCostumeShift(request, changes);
//        }
        costumeShiftDao.updateCostumeShift(costumeShift);
    }


    private boolean isChangesInWardOfCostumeShift(CostumeShiftUpdateRequest request, CostumeShift costumeShift) {
        Ward ward = wardDao.findWardById(request.wardId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Ward not found"
                        )
                );
        costumeShift.setWard(ward);
        ward.setCostumeShifts(List.of(costumeShift));
        return true;
    }


    private void updateTimingsArrangeCostumeShift(CostumeShiftUpdateRequest request, boolean changes) {
        List<TimingsArrange> timingsArrangeList = new ArrayList<>();
        for (TimingsArrangeUpdateRequest updateRequest : request.listShiftTimings()){
            TimingsArrange timingsArrange = timingsArrangeDao.getTimingArrangeById(updateRequest.id())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "No such shift"
                            )
                    );
            if (updateRequest.startFrom() != null){
                timingsArrange.setStartFrom(updateRequest.startFrom());
                changes = true;
            }if (updateRequest.toTime() != null){
                timingsArrange.setToTime(updateRequest.toTime());
                changes = true;
            }if (!changes) throw new ResourceNotFoundException("There is no update changes");

            timingsArrangeList.add(timingsArrange);
            timingsArrangeDao.updateTimingsArrange(timingsArrangeList);
        }
    }
}
