package com.azu.hospital.humanResource.employeeDetails.salaryDetails.dto;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.employeeDetails.salaryDetails.dao.SalaryDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaryDetailsDtoMapper {
  public SalaryDetailsDto toDto(SalaryDetails details) {
    return Optional.ofNullable(details)
            .map(s -> new SalaryDetailsDto(
                    s.getSalaryId(),
                    s.getType(),
                    s.getAmount(),
                    s.getNote(),
                    s.getEmployee().getEmployeeId()
            ))
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "There Is No Data For Returning"
                    )
            );
  }
}
