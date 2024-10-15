package com.azu.hospital.ph.StockMangment.Consumbles.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Getter
@Setter
public class ConsumableDto {

    private Long consumableId;
    private String consumableName;
    private String consumableCompany;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal consumableSellingPrice;
    private String barcode;
    private String expDate;
    private String proDate;
    private Currency typeOfCurrency;
    private BigDecimal exchange;
    private String description;
    private String bounce;
    private String consumableImage;
    private String consumablePharmacyPlace;
    private Long billId;
    private String supplierName;
    private Instant updateAt;


    public ConsumableDto(Long consumableId, String consumableName,
                         String consumableCompany, Integer quantity, BigDecimal price,
                         BigDecimal consumableSellingPrice, String barcode,
                         String expDate, String proDate, Currency typeOfCurrency, BigDecimal exchange,
                         String description,String bounce, String consumableImage, String consumablePharmacyPlace,
                         Long billId, String supplierName, Instant updateAt) {
        this.consumableId = consumableId;
        this.consumableName = consumableName;
        this.consumableCompany = consumableCompany;
        this.quantity = quantity;
        this.price = price;
        this.consumableSellingPrice = consumableSellingPrice;
        this.barcode = barcode;
        this.expDate = expDate;
        this.proDate = proDate;
        this.typeOfCurrency = typeOfCurrency;
        this.exchange = exchange;
        this.description = description;
        this.bounce = bounce;
        this.consumableImage = consumableImage;
        this.consumablePharmacyPlace = consumablePharmacyPlace;
        this.billId = billId;
        this.supplierName = supplierName;
        this.updateAt = updateAt;
    }

    public ConsumableDto(Long consumableId, String consumableName,
                         String consumableCompany, BigDecimal price,
                         String expDate, String description,String bounce, String consumableImage) {
        this.consumableId = consumableId;
        this.consumableName = consumableName;
        this.consumableCompany = consumableCompany;
        this.price = price;
        this.expDate = expDate;
        this.description = description;
        this.bounce = bounce;
        this.consumableImage = consumableImage;
    }

    public ConsumableDto(Long consumableId, String consumableName, Integer quantity, BigDecimal price, String bounce) {
        this.consumableId = consumableId;
        this.consumableName = consumableName;
        this.quantity = quantity;
        this.price = price;
        this.bounce = bounce;
    }



}
