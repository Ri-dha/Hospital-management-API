package com.azu.hospital.humanResource.employeeDetails.employee.dto;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDtoMapper {
    public EmployeeDto toDto(Employee employee) {
        return Optional.ofNullable(employee)
                .map(e -> new EmployeeDto(
                        e.getUser().getUsernameSpecial(),
                        e.getUser().getSpecialist(),
                        e.getUser().getGender(),
                        e.getUser().getMobile(),
                        e.getUser().getAge(),
                        e.getUser().getEmployeeDate(),
                        e.getUser().getId(),
                        e.getUser().getRoles(),
                        e.getStatus()
                ))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "There Is No Data For Returning"
                        )
                );
    }
}
