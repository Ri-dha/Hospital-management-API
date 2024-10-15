package com.azu.hospital.bulding.Shift.EmployeeShift.dao;

import com.azu.hospital.bulding.Shift.EmployeeShift.entity.EmployeeShift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeShiftDao {

    void createNewUserShift(EmployeeShift nurseShift);

    void createManyUsersShifts(Iterable<EmployeeShift> nurseShifts);

    void updateUserShift(EmployeeShift nurseShift);

    Optional<EmployeeShift> getUserShiftById(Long id);

    Page<EmployeeShift> getAllUserShifts(Pageable pageable);

    Boolean existsByUserIdAndShiftId(Long userId, Long shiftIdy);

    List<EmployeeShift> getUserShiftsByNurseId(Long nurseId , LocalDate date);

    Integer getMaxDayByUserIdAndShiftId(Long nurseId , Long shiftId);

    List<Integer> getDaysByUserIdAndShiftId(Long nurseId);
}
