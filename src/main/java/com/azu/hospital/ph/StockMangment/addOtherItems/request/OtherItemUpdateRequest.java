package com.azu.hospital.ph.StockMangment.addOtherItems.request;

import com.azu.hospital.utils.enums.DeviceType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record OtherItemUpdateRequest(
        String itemName,
        String itemCompany,
        Integer itemQuantity,
        BigDecimal itemBuyingPrice,
        String itemBarcode,
        String itemDescription,
        String serialNumber,
        String itemProDate,
        String deviceWarrantyDate,
        DeviceType deviceType,
        String bounce,
        @NotNull(message = "Image Require")
        @NotEmpty(message = "Image Require")
        MultipartFile itemImageUrl,

        List<MultipartFile> files
) {
}
