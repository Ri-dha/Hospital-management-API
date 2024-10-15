package com.azu.hospital.bulding.Shift.shift_timings.dao.dao_costume;

import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import com.azu.hospital.utils.enums.EnumRole;

import java.util.List;
import java.util.Optional;

public interface CostumeShiftDao {

    void addNewCostumeShift(CostumeShift request);

    void updateCostumeShift(CostumeShift update);

    Optional<CostumeShift> getCostumeShiftById(Long costumeId);

    List<CostumeShift> getAllCostumeShift();

    List<CostumeShift> getAllCostumeShiftByWardId(Long wardId, EnumRole role);

}
