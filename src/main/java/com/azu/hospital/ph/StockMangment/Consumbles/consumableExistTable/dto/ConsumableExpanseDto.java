package com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

public record ConsumableExpanseDto(
        Long id,
        Long consumableId,
        String consumableName,
        String consumableCompany,
        Integer quantity,
        BigDecimal price,
        BigDecimal consumableSellingPrice,
        String barcode,
        String expDate,
        String proDate,
        Currency typeOfCurrency,
        BigDecimal exchange,
        String description,
        String consumableImage,
        String consumablePharmacyPlace,
        Long billId,
        String supplierName,
        Instant createdAt,
        Instant updatedAt
) {
}
