package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dto;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dto.CustomSalaryDetailDto;

import java.time.Instant;
import java.util.List;

public record MainSalaryTableDto (
        Long id,
        Long userId,
        Double totalSalary,
        String date,
        Instant createdAt,
        Instant updatedAt,
        List<CustomSalaryDetailDto> bonusesAndDeductions

){
}
