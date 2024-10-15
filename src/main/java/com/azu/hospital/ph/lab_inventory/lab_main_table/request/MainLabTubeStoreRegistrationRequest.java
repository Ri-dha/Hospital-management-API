package com.azu.hospital.ph.lab_inventory.lab_main_table.request;

import com.azu.hospital.utils.enums.LabTubeStorageType;
import com.azu.hospital.utils.enums.storeHose.StoreHoseItemEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record MainLabTubeStoreRegistrationRequest(
        @NotNull(message = "Item name Should not be null")
        String tubeName,
        @NotNull(message = "Item name company Should not be null")
        String tubeCompany,
        @NotNull(message = "Item Quantity Should not be null")
        Integer quantity,

//        @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Price must be a valid number")
        @NumberFormat
        BigDecimal itemBuyingPrice,
        @NotEmpty(message = "Item Barcode Should not be null")
        @NotNull(message = "Item Barcode Should not be null")
        @NotBlank(message = "Item Barcode Should not be null")
        String barcode,
        String descriptions,
        @NotNull
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Product date should be in the format YYYY-MM-DD"
        )
        @DateTimeFormat(
                style = "Product date should be in the format YYYY-MM-DD"
        )
        String proDate,

        @NotNull
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Expiry date should be in the format YYYY-MM-DD"
        )
        @DateTimeFormat(
                style = "Expiry date should be in the format YYYY-MM-DD"
        )
        String expDate,
        String place,
        @NotNull(message = "storage Type required")
        LabTubeStorageType storageType,
        @NotNull(message = "Tube Type required")
        StoreHoseItemEnum type,
        MultipartFile image
) {
}
