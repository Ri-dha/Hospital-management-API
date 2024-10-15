package com.azu.hospital.bulding.Shift.EmployeeShift.dao;

import com.azu.hospital.bulding.Shift.EmployeeShift.entity.EmployeeShift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("UserShiftDataJpa")
public class EmployeeShiftDataJpa implements EmployeeShiftDao {


    private final EmployeeShiftRepository employeeShiftRepository;

    public EmployeeShiftDataJpa(EmployeeShiftRepository nurseShiftRepository) {
        this.employeeShiftRepository = nurseShiftRepository;
    }

    @Override
    public void createNewUserShift(EmployeeShift nurseShift) {
        employeeShiftRepository.save(nurseShift);
    }

    @Override
    public void createManyUsersShifts(Iterable<EmployeeShift> nurseShifts) {
        employeeShiftRepository.saveAll(nurseShifts);
    }

    @Override
    public void updateUserShift(EmployeeShift nurseShift) {
        employeeShiftRepository.save(nurseShift);
    }

    @Override
    public Optional<EmployeeShift> getUserShiftById(Long id) {
        return employeeShiftRepository.findById(id);
    }

    @Override
    public Page<EmployeeShift> getAllUserShifts(Pageable pageable) {
        return employeeShiftRepository.findAll(pageable);
    }

    @Override
    public Boolean existsByUserIdAndShiftId(Long nurseId, Long shiftId) {
        return employeeShiftRepository.existsByUserIdAndShiftId(nurseId,shiftId);
    }

    @Override
    public List<EmployeeShift> getUserShiftsByNurseId(Long nurseId , LocalDate date) {
        return employeeShiftRepository.getUserShiftsByNurseId(nurseId , date);
    }

    @Override
    public Integer getMaxDayByUserIdAndShiftId(Long nurseId, Long shiftId) {
        return employeeShiftRepository.getMaxDayByUserIdAndShiftId(nurseId,shiftId);
    }

    @Override
    public List<Integer> getDaysByUserIdAndShiftId(Long nurseId) {
        return employeeShiftRepository.getDaysByUserIdAndShiftId(nurseId);
    }
}
