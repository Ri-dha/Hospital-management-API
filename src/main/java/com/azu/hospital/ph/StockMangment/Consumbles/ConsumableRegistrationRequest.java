package com.azu.hospital.ph.StockMangment.Consumbles;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Currency;

public record ConsumableRegistrationRequest(
        @Column(
                nullable = false
        )
        @NotNull(
                message = "Consumable name should not be null"
        )
        @Size(
                min = 4, max = 55,
                message = "Items Name should be less than 50 char and more than 2 char"
        )
        String consumableName,
        String consumableCompany,
        @Column(
                nullable = false
        )
        @NotNull(
                message = "Quantity should not be null"
        )
        Integer quantity,
        @Column(
                nullable = false
        )
        @NotNull(
                message = "Price should not be null"
        )
        BigDecimal price,
        BigDecimal consumableSellingPrice,
        @Column(
                nullable = false,
                unique = true

        )
        @NotNull(
                message = "Barcode Should Not be Null"
        )
        @Size(
                min = 2,
                message = "Barcode should be less than 50 char and more than 2 char"
        )
        String barcode,
        @Column(
                nullable = false
        )
        @NotNull(
                message = "Expiry date should not be null"
        )
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Expiry date should be in the format YYYY-MM-DD"
        )
        @DateTimeFormat(
                style = "Expiry date should be in the format YYYY-MM-DD"
        )
        String expDate,
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Production date should be in the format YYYY-MM-DD"
        )
        @DateTimeFormat(
                style = "Product date should be in the format YYYY-MM-DD"
        )
        String proDate,
        @NotNull(message = "Type of Currency Require")
        Currency typeOfCurrency,
        @NotNull(
                message = "Money exchange Required"
        )
        BigDecimal exchange,
        String description,
        String bounce,
        String consumablePharmacyPlace,
        MultipartFile consumableImage
) {
}
