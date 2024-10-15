package com.azu.hospital.ph.main_data_store.drugs_item.entity;

import com.azu.hospital.ph.mediciens.type.MedicineType;

import java.math.BigDecimal;
import java.util.Currency;

public class DrugItemBuilder {

    private Long id;
    private String itemName;
    private String itemCompany;
    private String expDate;
    private String proDate;
    private String description;
    private String barcode;
    private String imageUrls;
    private BigDecimal exchange;
    private Currency typeOfCurrency;
    private Integer quantity;
    private String drugScientificName;
    private String dose;
    private String type;
    private String drugBonus;
    private String ndcNumber;
    private BigDecimal price;
    private BigDecimal drugSellingPrice;
    private Boolean isBack;

    public DrugItemBuilder() {
    }

    public DrugItemBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public DrugItemBuilder itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public DrugItemBuilder itemCompany(String itemCompany) {
        this.itemCompany = itemCompany;
        return this;
    }

    public DrugItemBuilder expDate(String expDate) {
        this.expDate = expDate;
        return this;
    }

    public DrugItemBuilder proDate(String proDate) {
        this.proDate = proDate;
        return this;
    }

    public DrugItemBuilder description(String description) {
        this.description = description;
        return this;
    }

    public DrugItemBuilder barcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public DrugItemBuilder imageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public DrugItemBuilder exchange(BigDecimal exchange) {
        this.exchange = exchange;
        return this;
    }

    public DrugItemBuilder typeOfCurrency(Currency typeOfCurrency) {
        this.typeOfCurrency = typeOfCurrency;
        return this;
    }

    public DrugItemBuilder quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public DrugItemBuilder drugScientificName(String drugScientificName) {
        this.drugScientificName = drugScientificName;
        return this;
    }

    public DrugItemBuilder dose(String dose) {
        this.dose = dose;
        return this;
    }

    public DrugItemBuilder type(String type) {
        this.type = type;
        return this;
    }

    public DrugItemBuilder drugBonus(String drugBonus) {
        this.drugBonus = drugBonus;
        return this;
    }

    public DrugItemBuilder ndcNumber(String ndcNumber) {
        this.ndcNumber = ndcNumber;
        return this;
    }

    public DrugItemBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public DrugItemBuilder drugSellingPrice(BigDecimal drugSellingPrice) {
        this.drugSellingPrice = drugSellingPrice;
        return this;
    }

    public DrugItemBuilder isBack(Boolean isBack) {
        this.isBack = isBack;
        return this;
    }

    public DrugsItem build() {
        DrugsItem drugsItem = new DrugsItem();
        drugsItem.setId(id);
        drugsItem.setItemName(itemName);
        drugsItem.setItemCompany(itemCompany);
        drugsItem.setExpDate(expDate);
        drugsItem.setProDate(proDate);
        drugsItem.setDescription(description);
        drugsItem.setBarcode(barcode);
        drugsItem.setImageUrls(imageUrls);
        drugsItem.setExchange(exchange);
        drugsItem.setTypeOfCurrency(typeOfCurrency);
        drugsItem.setQuantity(quantity);
        drugsItem.setDrugScientificName(drugScientificName);
        drugsItem.setDose(dose);
        drugsItem.setType(type);
        drugsItem.setDrugBonus(drugBonus);
        drugsItem.setNdcNumber(ndcNumber);
        drugsItem.setPrice(price);
        drugsItem.setDrugSellingPrice(drugSellingPrice);
        drugsItem.setIsBack(isBack);
        return drugsItem;
    }
}
