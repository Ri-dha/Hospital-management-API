package com.azu.hospital.bulding.department.dto;

import lombok.Data;

@Data
public class DepartmentUnitDto {
    private Long id;

    private String name;

    private Long managerId ;

    private Long managerAssistanceId;

    private String managerName ;

    private String managerAssistanceName;

    public DepartmentUnitDto(
            Long id,
            String name,
            Long managerId,
            Long managerAssistanceId,
            String managerName,
            String managerAssistanceName
    ) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.managerAssistanceId = managerAssistanceId;
        this.managerName = managerName;
        this.managerAssistanceName = managerAssistanceName;
    }
}
