package com.azu.hospital.bulding.Shift.arrange_shifts.services.user_shift;

import com.azu.hospital.bulding.Shift.arrange_shifts.dao.user_shift.UserShiftsTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShiftsTableGetService {

    private final UserShiftsTableDao userShiftsTableDao;

    @Autowired
    public UserShiftsTableGetService(UserShiftsTableDao userShiftsTableDao) {
        this.userShiftsTableDao = userShiftsTableDao;
    }



}
