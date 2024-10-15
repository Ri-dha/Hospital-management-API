package com.azu.hospital.ph.mediciens.request;


import com.azu.hospital.ph.mediciens.type.MedicineType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Currency;

public record DrugRegistrationRequest (
        @Column(
                nullable = false
        )
        @NotNull(
                message = "Drug Trade name Required"
        )
        @NotBlank(
                message = "Drug Trade name Required"
        )
        @NotEmpty(
                message = "Drug Trade name Required"
        )
        String drugTradeName,
        @Column(
                nullable = false
        )
        @NotNull(
                message = "Drug Scientific Name Required"
        )
        @NotBlank(
                message = "Drug Drug Scientific Name Required"
        )
        @NotEmpty(
                message = "Drug Drug Scientific Name Required"
        )
        String drugScientificName,
        String drugCompany,
        @Column(
                nullable = false
        )
        @NotNull(
                message = "Drug Dose Required"
        )
        @NotBlank(
                message = "Drug Dose Required"
        )
        @NotEmpty(
                message = "Drug Dose Required"
        )
        String dose,
        @Column(
                nullable = false
        )
        @NotNull(
                message = "Drug Type Required"
        )
        MedicineType type,
        @NotNull(
                message = "Quantity should not be null"
        )
        @NumberFormat
        Integer quantity,
        String drugBonus,
        String description,
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Expiry date should be in the format YYYY-MM-DD"
        )
        @DateTimeFormat(
                style = "Expiry date should be in the format YYYY-MM-DD"
        )
        @NotNull(message = "Expiry Date Require")
        @NotBlank(message = "Expiry Date Require")
        @NotEmpty(message = "Expiry Date Require")
        String expDate,
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Product date should be in the format YYYY-MM-DD"
        )
        @DateTimeFormat(
                style = "Product date should be in the format YYYY-MM-DD"
        )
        String proDate,
        @NotNull(message = "Barcode Require")
        @NotEmpty(message = "Barcode Require")
        @NotBlank(message = "Barcode Require")
        String barcode,
        String ndcNumber,
        @Column(
                nullable = false
        )
        @NotNull(
                message = "Drug Buying Price Required"
        )
        @NumberFormat
        BigDecimal price,

        @NumberFormat
        BigDecimal drugSellingPrice,
        @NotNull(message = "Type of Currency Require")
        Currency typOfCurrency,
        @NotNull(
                message = "Money exchange Required"
        )
        BigDecimal exchange,
        MultipartFile drugImageUrls

) {
}
