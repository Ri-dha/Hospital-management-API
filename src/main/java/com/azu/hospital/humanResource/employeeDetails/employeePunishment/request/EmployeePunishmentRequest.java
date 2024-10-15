package com.azu.hospital.humanResource.employeeDetails.employeePunishment.request;

import com.azu.hospital.humanResource.employeeDetails.employeeEnum.PunishmentTypesEnum;

public record EmployeePunishmentRequest(
        PunishmentTypesEnum punishmentType,
        String punishmentNote
) {
}
