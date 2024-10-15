package com.azu.hospital.bulding.Shift.shift_timings.dao.dao_costume;

import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository("CostumeShiftJpa")
public class CostumeShiftJpaDataAccess implements CostumeShiftDao{


    private final CostumeShiftRepository repository;

    @Autowired
    public CostumeShiftJpaDataAccess(CostumeShiftRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewCostumeShift(CostumeShift request) {
        repository.save(request);
    }

    @Override
    public void updateCostumeShift(CostumeShift update) {
      repository.save(update);
    }

    @Override
    public Optional<CostumeShift> getCostumeShiftById(Long costumeId) {
        return repository.findById(costumeId);
    }

    @Override
    public List<CostumeShift> getAllCostumeShift() {
        return repository.findAll();
    }

    @Override
    public List<CostumeShift> getAllCostumeShiftByWardId(Long wardId, EnumRole role) {
        return repository.findAllByWardWardId(wardId, role);
    }
}
