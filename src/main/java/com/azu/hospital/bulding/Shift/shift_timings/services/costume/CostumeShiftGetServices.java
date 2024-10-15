package com.azu.hospital.bulding.Shift.shift_timings.services.costume;

import com.azu.hospital.bulding.Shift.shift_timings.dao.dao_costume.CostumeShiftDao;
import com.azu.hospital.bulding.Shift.shift_timings.dto.costume.CostumeShiftDto;
import com.azu.hospital.bulding.Shift.shift_timings.dto.costume.CostumeShiftDtoMapper;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostumeShiftGetServices {

    private final CostumeShiftDao costumeShiftDao;
    private final WardDao wardDao;
    private final CostumeShiftDtoMapper mapper;

    @Autowired
    public CostumeShiftGetServices(CostumeShiftDao costumeShiftDao, WardDao wardDao, CostumeShiftDtoMapper mapper) {
        this.costumeShiftDao = costumeShiftDao;
        this.wardDao = wardDao;
        this.mapper = mapper;
    }

    public CostumeShiftDto getCostumeShiftById(Long id) {
        return costumeShiftDao.getCostumeShiftById(id)
                .map(mapper)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "No such shift availible for"
                        )
                );
    }

    public List<CostumeShiftDto> getAllCostumeShifts() {
        return costumeShiftDao.getAllCostumeShift()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }


    public List<CostumeShiftDto> getAllCostumeShiftsByWardId(Long wardId, EnumRole role) {
        return costumeShiftDao.getAllCostumeShiftByWardId(wardId, role)
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

}
