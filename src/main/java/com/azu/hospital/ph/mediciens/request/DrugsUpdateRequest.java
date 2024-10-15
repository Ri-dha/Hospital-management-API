package com.azu.hospital.ph.mediciens.request;

import com.azu.hospital.ph.mediciens.type.MedicineType;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Currency;

public record DrugsUpdateRequest(
        String drugTradeName,
        String drugScientificName,
        String drugCompany,
        String dose,
        MedicineType type,
        String customType,
        Integer quantity,
        String drugBonus,
        String description,
        String expDate,
        String proDate,
        String barcode,
        String ndcNumber,
        BigDecimal price,
        BigDecimal drugSellingPrice,
        Currency typOfCurrency,
        BigDecimal exchange,
        String drugPharmacyPlace,
        MultipartFile drugImageUrls

) {
}
