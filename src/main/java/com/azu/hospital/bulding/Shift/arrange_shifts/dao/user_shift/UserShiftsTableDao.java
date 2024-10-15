package com.azu.hospital.bulding.Shift.arrange_shifts.dao.user_shift;

import com.azu.hospital.bulding.Shift.arrange_shifts.entity.UserShiftsTable;

import java.util.List;
import java.util.Optional;

public sealed interface UserShiftsTableDao permits UserShiftsTableJpaDataAccess {

    void addListUserShiftsTable(List<UserShiftsTable> requests);

    void updateUserShiftsTable(UserShiftsTable update);

    Optional<UserShiftsTable> getUserShiftsTableById(Long id);

    List<UserShiftsTable> getAllUserShiftsTable();
}
