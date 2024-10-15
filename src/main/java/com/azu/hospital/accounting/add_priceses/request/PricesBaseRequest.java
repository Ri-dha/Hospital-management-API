package com.azu.hospital.accounting.add_priceses.request;

import com.azu.hospital.utils.enums.EnumItemType;

import java.math.BigDecimal;

public record PricesBaseRequest(
        String itemName,
        BigDecimal finalPrice,
        EnumItemType type,
        String note
) {
}
