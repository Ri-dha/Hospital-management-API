package com.azu.hospital.ph.main_data_store.drugs_item.dto;

import com.azu.hospital.ph.mediciens.type.MedicineType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;
@Getter
@Setter
public class DrugItemDto {

    private Long drugId;
    private String drugTradeName;
    private String drugScientificName;
    private String drugCompany;
    private String dose;
    private String type;
    private Integer quantity;
    private String drugBonus;
    private String description;
    private String expDate;
    private String proDate;
    private String barcode;
    private String ndcNumber;
    private BigDecimal price;
    private BigDecimal drugSellingPrice;
    private Currency typOfCurrency;
    private BigDecimal exchange;
    private boolean isBack;
    private boolean isDrugExists;
    private String drugImageUrls;
    private Long billId;
    private String supplerDetails;

    public DrugItemDto(
            Long drugId, String drugTradeName, String drugScientificName,
            String drugCompany, String dose, String type, Integer quantity,
            String drugBonus, String description, String expDate, String proDate, String barcode,
            String ndcNumber, BigDecimal price, BigDecimal drugSellingPrice, Currency typOfCurrency,
            BigDecimal exchange, boolean isBack, boolean isDrugExists, String drugImageUrls, Long billId, String supplerDetails) {
        this.drugId = drugId;
        this.drugTradeName = drugTradeName;
        this.drugScientificName = drugScientificName;
        this.drugCompany = drugCompany;
        this.dose = dose;
        this.type = type;
        this.quantity = quantity;
        this.drugBonus = drugBonus;
        this.description = description;
        this.expDate = expDate;
        this.proDate = proDate;
        this.barcode = barcode;
        this.ndcNumber = ndcNumber;
        this.price = price;
        this.drugSellingPrice = drugSellingPrice;
        this.typOfCurrency = typOfCurrency;
        this.exchange = exchange;
        this.isBack = isBack;
        this.isDrugExists = isDrugExists;
        this.drugImageUrls = drugImageUrls;
        this.billId = billId;
        this.supplerDetails = supplerDetails;
    }



    public static DrugItemDtoBuilder builder() {
        return new DrugItemDtoBuilder();
    }

    public static class DrugItemDtoBuilder {
        private Long drugId;
        private String drugTradeName;
        private String drugScientificName;
        private String drugCompany;
        private String dose;
        private String type;
        private Integer quantity;
        private String drugBonus;
        private String description;
        private String expDate;
        private String proDate;
        private String barcode;
        private String ndcNumber;
        private BigDecimal price;
        private boolean isDrugExists;
        private BigDecimal drugSellingPrice;
        private Currency typOfCurrency;
        private BigDecimal exchange;
        private  boolean isBack;
        private String drugImageUrls;

        private Long billId;
        private String supplerDetails;

        DrugItemDtoBuilder() {
        }

        public DrugItemDtoBuilder drugId(Long drugId) {
            this.drugId = drugId;
            return this;
        }

        public DrugItemDtoBuilder drugTradeName(String drugTradeName) {
            this.drugTradeName = drugTradeName;
            return this;
        }

        public DrugItemDtoBuilder drugScientificName(String drugScientificName) {
            this.drugScientificName = drugScientificName;
            return this;
        }

        public DrugItemDtoBuilder drugCompany(String drugCompany) {
            this.drugCompany = drugCompany;
            return this;
        }

        public DrugItemDtoBuilder dose(String dose) {
            this.dose = dose;
            return this;
        }

        public DrugItemDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public DrugItemDtoBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public DrugItemDtoBuilder drugBonus(String drugBonus) {
            this.drugBonus = drugBonus;
            return this;
        }

        public DrugItemDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public DrugItemDtoBuilder expDate(String expDate) {
            this.expDate = expDate;
            return this;
        }

        public DrugItemDtoBuilder proDate(String proDate) {
            this.proDate = proDate;
            return this;
        }

        public DrugItemDtoBuilder barcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public DrugItemDtoBuilder ndcNumber(String ndcNumber) {
            this.ndcNumber = ndcNumber;
            return this;
        }

        public DrugItemDtoBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public DrugItemDtoBuilder drugSellingPrice(BigDecimal drugSellingPrice) {
            this.drugSellingPrice = drugSellingPrice;
            return this;
        }

        public DrugItemDtoBuilder typOfCurrency(Currency typOfCurrency) {
            this.typOfCurrency = typOfCurrency;
            return this;
        }

        public DrugItemDtoBuilder exchange(BigDecimal exchange) {
            this.exchange = exchange;
            return this;
        }
        public DrugItemDtoBuilder isBack(boolean isBack) {
            this.isBack = isBack;
            return this;
        }
        public DrugItemDtoBuilder isDrugExists(boolean isDrugExists) {
            this.isDrugExists = isDrugExists;
            return this;
        }

        public DrugItemDtoBuilder drugImageUrls(String drugImageUrls) {
            this.drugImageUrls = drugImageUrls;
            return this;
        }

        public DrugItemDtoBuilder billId(Long billId) {
            this.billId = billId;
            return this;
        }
        public DrugItemDtoBuilder supplier(String supplerDetails) {
            this.supplerDetails = supplerDetails;
            return this;
        }
        public DrugItemDto build() {
            return new DrugItemDto(
                    this.drugId, this.drugTradeName, this.drugScientificName, this.drugCompany, this.dose, this.type,
                    this.quantity, this.drugBonus, this.description, this.expDate, this.proDate, this.barcode, this.ndcNumber,
                    this.price, this.drugSellingPrice, this.typOfCurrency, this.exchange,this.isBack,
                    this.isDrugExists, this.drugImageUrls , this.billId, this.supplerDetails);
        }

        @Override
        public String toString() {
            return "DrugItemDtoBuilder{" +
                    "drugId=" + drugId +
                    ", drugTradeName='" + drugTradeName + '\'' +
                    ", drugScientificName='" + drugScientificName + '\'' +
                    ", drugCompany='" + drugCompany + '\'' +
                    ", dose='" + dose + '\'' +
                    ", type=" + type +
                    ", quantity=" + quantity +
                    ", drugBonus='" + drugBonus + '\'' +
                    ", description='" + description + '\'' +
                    ", expDate='" + expDate + '\'' +
                    ", proDate='" + proDate + '\'' +
                    ", barcode='" + barcode + '\'' +
                    ", ndcNumber='" + ndcNumber + '\'' +
                    ", price=" + price +
                    ", isDrugExists=" + isDrugExists +
                    ", drugSellingPrice=" + drugSellingPrice +
                    ", typOfCurrency=" + typOfCurrency +
                    ", exchange=" + exchange +
                    ", isBack=" + isBack +
                    ", drugImageUrls='" + drugImageUrls + '\'' +
                    ", billId=" + billId +
                    ", supplerDetails=" + supplerDetails +
                    '}';
        }
    }
}
