package com.azu.hospital.ph.StockMangment.addOtherItems.orders.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class DeviceExistDto {

    private Long itemId;

    private String itemName;

    private String deviceType;

    private Integer itemQuantity;

    private Long deviceInMainTableStoreId;

    private String serialNumber;

    private String itemBarcode;

    private Boolean isDeviceWorking;

    private Boolean isAvailable;
    private String itemCompany;
    private BigDecimal itemBuyingPrice;
    private String itemDescription;
    private String itemProDate;
    private String itemImageUrl;
    private List<String> files;
    private Long billId;
    private String supplierName;
    private String inventoryPlace;
    private Instant updateAt;

    private String bonus;

    private String deviceWarrantyDate;




    public DeviceExistDto() {
    }


    public DeviceExistDto(
            Long itemId,
            String itemName,
            String deviceType,
            Integer itemQuantity,
            Long deviceInMainTableStoreId,
            String serialNumber,
            String itemBarcode,
            Boolean isDeviceWorking,
            Boolean isAvailable,
            String itemCompany,
            BigDecimal itemBuyingPrice,
            String itemDescription,
            String itemProDate,
            String itemImageUrl,
            List<String> files,
            Long billId,
            String supplierName,
            String inventoryPlace,
            Instant updateAt,
            String bonus ,
            String deviceWarrantyDate
    ) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.deviceType = deviceType;
        this.itemQuantity = itemQuantity;
        this.deviceInMainTableStoreId = deviceInMainTableStoreId;
        this.serialNumber = serialNumber;
        this.itemBarcode = itemBarcode;
        this.isDeviceWorking = isDeviceWorking;
        this.isAvailable = isAvailable;
        this.itemCompany = itemCompany;
        this.itemBuyingPrice = itemBuyingPrice;
        this.itemDescription = itemDescription;
        this.itemProDate = itemProDate;
        this.itemImageUrl = itemImageUrl;
        this.files = files;
        this.billId = billId;
        this.supplierName = supplierName;
        this.inventoryPlace = inventoryPlace;
        this.updateAt = updateAt;
        this.bonus = bonus;
        this.deviceWarrantyDate = deviceWarrantyDate;
    }
}
