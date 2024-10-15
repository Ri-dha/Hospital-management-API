package com.azu.hospital.bulding.Shift.dao;

import com.azu.hospital.bulding.Shift.entity.Shift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("ShiftDataJpa")
public class ShiftDataJpa implements ShiftDao {

    private final ShiftRepository shiftRepository;

    public ShiftDataJpa(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public void createNewShift(Shift shift) {
        shiftRepository.save(shift);
    }

    @Override
    public void updateShift(Shift shift) {
        shiftRepository.save(shift);
    }

    @Override
    public Optional<Shift> getShiftById(Long id) {
        return shiftRepository.findById(id);
    }

    @Override
    public Page<Shift> getAllShifts(Pageable pageable) {
        return shiftRepository.findAll(pageable);
    }
}
