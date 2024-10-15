package com.azu.hospital.bulding.unit.dto;

import com.azu.hospital.bulding.unit.unitDevice.dto.UnitDeviceDto;
import com.azu.hospital.bulding.unit.unitFurniture.dto.UnitFurnitureDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class UnitDto {

    private Long id;

    private String name;

    private Long managerId ;

    private Long managerAssistanceId;

    private String managerName ;

    private String managerAssistanceName;

    private Long floorId;

    private String floorNumber;

    private String floorName;

    private Long departmentId;

    private String departmentName;

    private List<UnitDeviceDto> devices;

    private List<UnitFurnitureDto> furnitures;

    private Instant createdAt;

    private Instant updatedAt;

    public UnitDto(Long id, String name, Long managerId, Long managerAssistanceId, String managerName,
                   String managerAssistanceName, Long floorId, String floorNumber, String floorName,
                   Long departmentId, String departmentName, List<UnitDeviceDto> devices,
                   List<UnitFurnitureDto> furnitures, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.managerAssistanceId = managerAssistanceId;
        this.managerName = managerName;
        this.managerAssistanceName = managerAssistanceName;
        this.floorId = floorId;
        this.floorNumber = floorNumber;
        this.floorName = floorName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.devices = devices;
        this.furnitures = furnitures;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
