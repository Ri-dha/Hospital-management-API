package com.azu.hospital.bulding.Shift.arrange_shifts.dao.user_shift;

import com.azu.hospital.bulding.Shift.arrange_shifts.entity.UserShiftsTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserShiftsTableRepository extends JpaRepository<UserShiftsTable, Long> {
}
