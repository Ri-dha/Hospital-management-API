package com.azu.hospital.bulding.Shift.EmployeeShift.dto;

import com.azu.hospital.bulding.Shift.EmployeeShift.entity.EmployeeShift;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeShiftDtoMapper implements Function<EmployeeShift, EmployeeShiftDto> {
    @Override
    public EmployeeShiftDto apply(EmployeeShift employeeShift) {
        return new EmployeeShiftDto(
                employeeShift.getId(),
                employeeShift.getUser().getId(),
                employeeShift.getUser().getUsernameSpecial(),
                employeeShift.getShift().getId(),
                employeeShift.getShift().getName(),
                employeeShift.getWard().getWardId(),
                employeeShift.getWard().getName(),
                employeeShift.getCreatedAt(),
                employeeShift.getUpdatedAt()
        );
    }

}
