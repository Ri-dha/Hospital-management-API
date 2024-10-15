package com.azu.hospital.bulding.Shift.dao;

import com.azu.hospital.bulding.Shift.entity.Shift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ShiftDao {

    void createNewShift(Shift shift);

    void updateShift(Shift shift);
    Optional<Shift> getShiftById(Long id);

    Page<Shift> getAllShifts(Pageable pageable);



}
