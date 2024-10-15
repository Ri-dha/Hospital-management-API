package com.azu.hospital.bulding.department.dto;

import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.bulding.unit.dto.UnitDtoMapper;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.ward.dto.WardDtoMapper;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DepartmentDtoMapper implements Function<Department, DepartmentDto> {

    private final WardDtoMapper wardDtoMapper;

    private final UnitDtoMapper unitDtoMapper;


    public DepartmentDtoMapper(WardDtoMapper wardDtoMapper, UnitDtoMapper unitDtoMapper) {
        this.wardDtoMapper = wardDtoMapper;
        this.unitDtoMapper = unitDtoMapper;
    }

    public DepartmentWardDto wardToDto(Ward ward){
        return Optional.ofNullable(ward)
                .map(w -> new DepartmentWardDto(
                        w.getWardId(),
                        w.getName(),
                        w.getDoctor().getId(),
                        w.getNurse().getId(),
                        w.getDoctor().getUsernameSpecial(),
                        w.getNurse().getUsernameSpecial()
                ))
                .orElseThrow(() -> new BadRequestException(
                        "Ward is null or empty for some reason"
                ));
    }

    public DepartmentUnitDto unitToDto(Unit unit){
        return Optional.ofNullable(unit)
                .map(u -> new DepartmentUnitDto(
                        u.getUnitId(),
                        u.getName(),
                        u.getManager().getId(),
                        u.getManagerAssistance().getId(),
                        u.getManager().getUsernameSpecial(),
                        u.getManagerAssistance().getUsernameSpecial()
                ))
                .orElseThrow(() -> new BadRequestException(
                        "Unit is null or empty for some reason"
                ));
    }



    public DtoForReturnIdOnly toDtoForDepartmentId(Department department){
        return new DtoForReturnIdOnly(
                department.getDepId()
        );
    }

    @Override
    public DepartmentDto apply(Department department) {
        Long managerId = null;
        String managerUsername = null;
        if (department.getManager() != null) {
            managerId = department.getManager().getId();
            managerUsername = department.getManager().getUsernameSpecial();
        }

        Long assistantManagerId = null;
        String assistantManagerUsername = null;
        if (department.getDepartmentMangerAssistance() != null) {
            assistantManagerId = department.getDepartmentMangerAssistance().getId();
            assistantManagerUsername = department.getDepartmentMangerAssistance().getUsernameSpecial();
        }

        return new DepartmentDto(
                department.getDepId(),
                department.getDepartmentName(),
                managerId,
                managerUsername,
                assistantManagerId,
                assistantManagerUsername,
                department.getFloor().getFloorId(),
                department.getWards()
                        .stream()
                        .map(wardDtoMapper)
                        .collect(Collectors.toList()),
                department.getUnits()
                        .stream()
                        .map(unitDtoMapper)
                        .collect(Collectors.toList()),
                department.getCreatedAt(),
                department.getUpdatedAt()
        );
    }

}
