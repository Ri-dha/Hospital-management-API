package com.azu.hospital.ph.StockMangment.addOtherItems.request;


import com.azu.hospital.utils.enums.DeviceType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class OtherItemRegistrationRequest {

    @NotEmpty(message = "Item name Should not be null")
    @NotNull(message = "Item name Should not be null")
    @NotBlank(message = "Item name Should not be null")
    @Size(min = 4, max = 100, message = "Item name Should not be not less than 4 character")
    private String itemName;

    @NotEmpty(message = "Item name company Should not be null")
    @NotNull(message = "Item name company Should not be null")
    @NotBlank(message = "Item name company Should not be null")
    @Size(min = 4, max = 100, message = "Item name company Should not be not less than 4 character")
    private String itemCompany;

    @NotNull(message = "Item Quantity Should not be null")
    private Integer itemQuantity;

    @NotNull(message = "Item Prise Should not be null")
    private BigDecimal itemBuyingPrice;

    @NotEmpty(message = "Item Barcode Should not be null")
    @NotNull(message = "Item Barcode Should not be null")
    @NotBlank(message = "Item Barcode Should not be null")
    private String itemBarcode;

    private String itemDescription;

    private String serialNumber;

    private String itemProDate;

    private String deviceWarrantyDate;

    @NotNull(message = "Item type should not be null")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    private String bonus;
    private MultipartFile itemImageUrl;

    @Nullable
    private List<MultipartFile> files = new ArrayList<>();


    public OtherItemRegistrationRequest() {
    }


    @JsonCreator
    public OtherItemRegistrationRequest(
            @JsonProperty("itemName") String itemName,
            @JsonProperty("itemCompany") String itemCompany,
            @JsonProperty("itemQuantity") Integer itemQuantity,
            @JsonProperty("itemBuyingPrice") BigDecimal itemBuyingPrice,
            @JsonProperty("itemBarcode") String itemBarcode,
            @JsonProperty("itemDescription") String itemDescription,
            @JsonProperty("serialNumber") String serialNumber,
            @JsonProperty("itemProDate") String itemProDate,
            @JsonProperty("deviceWarrantyDate") String deviceWarrantyDate,
            @JsonProperty("deviceType") DeviceType deviceType,
            @JsonProperty("bonus") String bonus,
            @JsonProperty("itemImageUrl") MultipartFile itemImageUrl,
            @Nullable @JsonProperty("files") List<MultipartFile> files
    ) {
        this.itemName = itemName;
        this.itemCompany = itemCompany;
        this.itemQuantity = itemQuantity;
        this.itemBuyingPrice = itemBuyingPrice;
        this.itemBarcode = itemBarcode;
        this.itemDescription = itemDescription;
        this.serialNumber = serialNumber;
        this.itemProDate = itemProDate;
        this.deviceWarrantyDate = deviceWarrantyDate;
        this.deviceType = deviceType;
        this.bonus = bonus;
        this.itemImageUrl = itemImageUrl;
        this.files =files;
    }

}