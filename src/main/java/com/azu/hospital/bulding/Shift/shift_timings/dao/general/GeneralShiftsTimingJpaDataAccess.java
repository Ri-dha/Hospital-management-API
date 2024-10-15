package com.azu.hospital.bulding.Shift.shift_timings.dao.general;

import com.azu.hospital.bulding.Shift.shift_timings.entity.GeneralShiftsTiming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("GeneralShiftsTimingJpa")
public class GeneralShiftsTimingJpaDataAccess implements GeneralShiftTimingDao{

    private final GeneralShiftTimingRepository repository;

    @Autowired
    public GeneralShiftsTimingJpaDataAccess(GeneralShiftTimingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addGeneralShiftTiming(GeneralShiftsTiming request) {
        repository.save(request);
    }

    @Override
    public void updateGeneralShiftsTiming(GeneralShiftsTiming update) {
     repository.save(update);
    }

    @Override
    public Optional<GeneralShiftsTiming> getGeneralShiftsTimingById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<GeneralShiftsTiming> getAllGeneralShiftsTiming() {
        return repository.findAll();
    }
}
