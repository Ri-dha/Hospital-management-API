package com.azu.hospital.bulding.ward.dto;

import com.azu.hospital.bulding.ward.wardDevice.dto.WardDeviceDto;
import com.azu.hospital.bulding.ward.wardFurniture.dto.WardFurnitureDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class WardDto {

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

    private List<WardDeviceDto> devices;

    private List<WardFurnitureDto> furnitures;

    private Instant createdAt;

    private Instant updatedAt;

    public WardDto(Long id, String name,
                   Long managerId,
                   Long managerAssistanceId,
                   String managerAssistanceName,
                   String managerName,
                   Long floorId, String floorNumber, String floorName,
                   Long departmentId, String departmentName, List<WardDeviceDto> devices,
                   List<WardFurnitureDto> furnitures, Instant createdAt, Instant updatedAt) {
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
