package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dto;

import java.time.Instant;

public record CustomSalaryDetailDto(
        Long id,
        String title,
        Double amount,
        Boolean isDown,
        String note,

        Boolean isDeleted,

        Instant createdAt,
        Instant updatedAt
) {
}
