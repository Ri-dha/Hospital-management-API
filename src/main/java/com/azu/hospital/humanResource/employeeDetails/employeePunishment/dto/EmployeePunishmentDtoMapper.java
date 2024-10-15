package com.azu.hospital.humanResource.employeeDetails.employeePunishment.dto;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.dao.EmployeePunishment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeePunishmentDtoMapper {
  public EmployeePunishmentDto toDto(EmployeePunishment punishment) {
    return Optional.ofNullable(punishment)
            .map(p -> new EmployeePunishmentDto(
                    p.getPunishmentId(),
                    p.getPunishmentType(),
                    p.getPunishmentNote(),
                    p.getEmployee().getEmployeeId()
            ))
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "There Is No Data For Returning"
                    )
            );
  }
}
