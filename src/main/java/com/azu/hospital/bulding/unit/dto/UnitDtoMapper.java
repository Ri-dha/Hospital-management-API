package com.azu.hospital.bulding.unit.dto;

import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.unit.unitDevice.dto.UnitDeviceDto;
import com.azu.hospital.bulding.unit.unitDevice.dto.UnitDeviceDtoMapper;
import com.azu.hospital.bulding.unit.unitFurniture.dto.UnitFurnitureDto;
import com.azu.hospital.bulding.unit.unitFurniture.dto.UnitFurnitureDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UnitDtoMapper implements Function<Unit, UnitDto>{
    private final UnitDeviceDtoMapper deviceMapper;
    private final UnitFurnitureDtoMapper furnitureMapper;

    public UnitDtoMapper(UnitDeviceDtoMapper deviceMapper, UnitFurnitureDtoMapper furnitureMapper) {
        this.deviceMapper = deviceMapper;
        this.furnitureMapper = furnitureMapper;
    }

    public UnitDto toDto(Unit unit) {
        return new UnitDto(
                unit.getUnitId(),
                unit.getName(),
                unit.getManager().getId(),
                unit.getManagerAssistance().getId(),
                unit.getManager().getUsernameSpecial(),
                unit.getManagerAssistance().getUsernameSpecial(),
                unit.getFloor().getFloorId(),
                unit.getFloor().getFloorNumber(),
                unit.getFloor().getFloorName(),
                unit.getDepartment().getDepId(),
                unit.getDepartment().getDepartmentName(),
                mapUnitDevices(unit),
                mapUnitFurniture(unit),
                unit.getCreatedAt(),
                unit.getUpdatedAt() == null ? null : unit.getUpdatedAt()
        );
    }

    private List<UnitDeviceDto> mapUnitDevices(Unit unit) {
        return unit.getUnitDevices()
                .stream()
                .map(deviceMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<UnitFurnitureDto> mapUnitFurniture(Unit unit) {
        return unit.getUnitFurniture()
                .stream()
                .map(furnitureMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UnitDto apply(Unit unit) {
        return new UnitDto(
                unit.getUnitId(),
                unit.getName(),
                unit.getManager().getId(),
                unit.getManagerAssistance().getId(),
                unit.getManager().getUsername(),
                unit.getManagerAssistance().getUsername(),
                unit.getFloor().getFloorId(),
                unit.getFloor().getFloorNumber(),
                unit.getFloor().getFloorName(),
                unit.getDepartment().getDepId(),
                unit.getDepartment().getDepartmentName(),
                mapUnitDevices(unit),
                mapUnitFurniture(unit),
                unit.getCreatedAt(),
                unit.getUpdatedAt() == null ? null : unit.getUpdatedAt()
        );
    }
}
