package com.azu.hospital.lab_collection.lab_test_main_table.dto;

import java.math.BigDecimal;

public record LabTestMainTableDto(
        Long testId,
        String testName,
        String note,
        BigDecimal price,
        String normalValue
) {
}
