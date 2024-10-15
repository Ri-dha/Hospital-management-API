package com.azu.hospital.ph.StockMangment.Consumbles;

import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Currency;

public record ConsumableUpdateRequest(

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
        String bounce,
        @Nullable
        MultipartFile consumableImage,
        String consumablePharmacyPlace
) {
}
