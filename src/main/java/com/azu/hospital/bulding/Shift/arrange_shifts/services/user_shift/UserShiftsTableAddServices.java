package com.azu.hospital.bulding.Shift.arrange_shifts.services.user_shift;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.bulding.Shift.arrange_shifts.entity.UserShiftsTable;
import com.azu.hospital.bulding.Shift.arrange_shifts.dao.user_shift.UserShiftsTableDao;
import com.azu.hospital.bulding.Shift.shift_timings.dao.dao_costume.CostumeShiftDao;
import com.azu.hospital.bulding.Shift.shift_timings.dao.general.GeneralShiftTimingDao;
import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import com.azu.hospital.bulding.Shift.shift_timings.entity.GeneralShiftsTiming;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserShiftsTableAddServices {

    private final UserShiftsTableDao userShiftsTableDao;
    private final CostumeShiftDao costumeShiftDao;
    private final GeneralShiftTimingDao generalShiftTimingDao;
    private final BaseUserDao baseUserDao;
    private final WardDao wardDao;

    @Autowired
    public UserShiftsTableAddServices(
            UserShiftsTableDao userShiftsTableDao,
            CostumeShiftDao costumeShiftDao,
            GeneralShiftTimingDao generalShiftTimingDao,
            BaseUserDao baseUserDao, WardDao wardDao) {
        this.userShiftsTableDao = userShiftsTableDao;
        this.costumeShiftDao = costumeShiftDao;
        this.generalShiftTimingDao = generalShiftTimingDao;
        this.baseUserDao = baseUserDao;
        this.wardDao = wardDao;
    }



    @Transactional
    public void insertUserShiftsTable(long userId, Long wardId, long shiftId){

        UserShiftsTable userShiftsTable = new UserShiftsTable();
        baseUserSetMethodAndCheck(userId, userShiftsTable);
        wardSetMethodAndCheck(wardId, userShiftsTable);
        shiftMethodTypeCostumeAndGeneralShifts(shiftId, userShiftsTable);
        userShiftsTableDao.addListUserShiftsTable(List.of(userShiftsTable));

    }

    private void baseUserSetMethodAndCheck(long userId, UserShiftsTable userShiftsTable) {
        BaseUser baseUser = baseUserDao.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User not found"
                        )
                );
        userShiftsTable.setBaseUser(baseUser);
        baseUser.setUserShiftsTable(List.of(userShiftsTable));
    }

    private void wardSetMethodAndCheck(Long wardId, UserShiftsTable userShiftsTable) {
        Ward ward = wardDao.findWardById(wardId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "ward not found"
                        )
                );
        userShiftsTable.setWardId(ward.getWardId());
        ward.setUserShiftsTablesWards(List.of(userShiftsTable));
    }

    private void shiftMethodTypeCostumeAndGeneralShifts(long shiftId, UserShiftsTable userShiftsTable) {
        Optional<CostumeShift> costumeShift = costumeShiftDao.getCostumeShiftById(shiftId);
        Optional<GeneralShiftsTiming> generalShiftsTiming= generalShiftTimingDao.getGeneralShiftsTimingById(shiftId);

        if (costumeShift.isPresent()) {
            CostumeShift shift = costumeShift.get();
            userShiftsTable.setShiftId(List.of(shift.getCostumeId()));
            shift.setUserShiftsTablesCostumes(List.of(userShiftsTable));
        }
        if (generalShiftsTiming.isPresent()) {
            GeneralShiftsTiming timings = generalShiftsTiming.get();
            userShiftsTable.setGeneralShiftsTimings(List.of(timings));
            timings.setGeneralId(timings.getGeneralId());
        }else {
            throw new ResourceNotFoundException(
                    "There is no Shift available"
            );
        }
    }
}
