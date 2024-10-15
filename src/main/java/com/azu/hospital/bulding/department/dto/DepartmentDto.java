package com.azu.hospital.bulding.department.dto;



import com.azu.hospital.bulding.unit.dto.UnitDto;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.ward.dto.WardDto;
import com.azu.hospital.bulding.ward.dto.WardDtoMapper;
import com.azu.hospital.bulding.ward.entity.Ward;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class DepartmentDto {

    private Long depId;

    private String departmentName;

    @Nullable
    private Long departmentMangerId;

    private String departmentMangerName;
    @Nullable
    private Long departmentMangerAssistanceId;

    private String departmentMangerAssistanceName;

    private Long floorId;

    private List<WardDto> wards;

    private List<UnitDto> units;

    private Instant createdAt;

    private Instant updatedAt;

    public DepartmentDto() {
    }

    public DepartmentDto(
            Long depId,
            String departmentName,
            @Nullable Long departmentMangerId,
            String departmentMangerName,
            @Nullable Long departmentMangerAssistanceId,
            String departmentMangerAssistanceName,
            Long floorId,
            List<WardDto> wards,
            List<UnitDto> units,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.depId = depId;
        this.departmentName = departmentName;
        this.departmentMangerId = departmentMangerId;
        this.departmentMangerName = departmentMangerName;
        this.departmentMangerAssistanceId = departmentMangerAssistanceId;
        this.departmentMangerAssistanceName = departmentMangerAssistanceName;
        this.floorId = floorId;
        this.wards = wards;
        this.units = units;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
