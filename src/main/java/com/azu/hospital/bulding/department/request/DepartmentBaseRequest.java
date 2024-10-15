package com.azu.hospital.bulding.department.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class DepartmentBaseRequest {

    @NotNull(message = "Department Name Require")
    @NotBlank(message = "Department Name Require")
    @NotEmpty(message = "Department Name Require")
    private String departmentName;

    private Long userId;
    private Long userAssistanceId;


    public DepartmentBaseRequest() {
    }

    public DepartmentBaseRequest(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentBaseRequest(String departmentName, Long userId, Long userAssistanceId) {
        this.departmentName = departmentName;
        this.userId = userId;
        this.userAssistanceId = userAssistanceId;
    }
}
