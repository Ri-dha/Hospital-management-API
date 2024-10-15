package com.azu.hospital.bulding.ward.dto;

import com.azu.hospital.bulding.ward.wardDevice.dto.WardDeviceDto;
import com.azu.hospital.bulding.ward.wardDevice.dto.WardDeviceDtoMapper;
import com.azu.hospital.bulding.ward.wardFurniture.dto.WardFurnitureDto;
import com.azu.hospital.bulding.ward.wardFurniture.dto.WardFurnitureDtoMapper;
import com.azu.hospital.bulding.ward.entity.Ward;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WardDtoMapper implements Function<Ward, WardDto> {
    private final WardDeviceDtoMapper deviceMapper;
    private final WardFurnitureDtoMapper furnitureMapper;

    public WardDtoMapper(WardDeviceDtoMapper deviceMapper, WardFurnitureDtoMapper furnitureMapper) {
        this.deviceMapper = deviceMapper;
        this.furnitureMapper = furnitureMapper;
    }

    private List<WardDeviceDto> mapWardDevices(Ward ward) {
        return ward.getWardDevices()
                .stream()
                .map(deviceMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<WardFurnitureDto> mapWardFurniture(Ward ward) {
        return ward.getWardFurniture()
                .stream()
                .map(furnitureMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public WardDto apply(Ward ward) {
        return new WardDto(
                ward.getWardId(),
                ward.getName(),
                ward.getDoctor().getId(),
                ward.getNurse().getId(),
                ward.getNurse().getUsernameSpecial(),
                ward.getDoctor().getUsernameSpecial(),
                ward.getFloor().getFloorId(),
                ward.getFloor().getFloorNumber(),
                ward.getFloor().getFloorName(),
                ward.getDepartment().getDepId(),
                ward.getDepartment().getDepartmentName(),
                mapWardDevices(ward),
                mapWardFurniture(ward),
                ward.getCreatedAt(),
                ward.getUpdatedAt()
        );
    }
}
