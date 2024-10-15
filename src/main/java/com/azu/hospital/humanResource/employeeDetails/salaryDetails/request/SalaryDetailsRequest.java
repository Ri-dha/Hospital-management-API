package com.azu.hospital.humanResource.employeeDetails.salaryDetails.request;

import com.azu.hospital.humanResource.employeeDetails.employeeEnum.SalaryComponentsType;

import java.math.BigDecimal;

public record SalaryDetailsRequest(
        SalaryComponentsType type,
        BigDecimal amount,
        String note

) {
}
