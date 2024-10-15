package com.azu.hospital.accounting.add_priceses.dto;

import com.azu.hospital.utils.enums.EnumItemType;
import com.azu.hospital.utils.enums.patient.BillState;

import java.math.BigDecimal;
import java.time.Instant;

public record PricesDto(
         Long id,
         String itemName,
         BigDecimal finalPrice,
         EnumItemType type,
         String note,
         BillState billState,
         Instant createdAt,
         Instant updatedAt
) {
}
