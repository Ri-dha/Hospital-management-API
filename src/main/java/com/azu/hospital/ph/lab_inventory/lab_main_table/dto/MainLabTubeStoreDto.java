package com.azu.hospital.ph.lab_inventory.lab_main_table.dto;

import java.math.BigDecimal;

public record MainLabTubeStoreDto(
        Long id,
        String tubeName,
        String tubeCompany,
        Integer quantity,
        BigDecimal itemBuyingPrice,
        String barcode,
        String descriptions,
        String proDate,
        String expDate,
        String place,
        String storageType,
        String type,
        String image,
        Long billId,
        String supplierName
) {


    public static MainLabTubeStoreDtoBuilder builder() {
        return new MainLabTubeStoreDtoBuilder();
    }

    public static class MainLabTubeStoreDtoBuilder {
        private Long id;
        private String tubeName;
        private String tubeCompany;
        private Integer quantity;
        private BigDecimal itemBuyingPrice;
        private String barcode;
        private String descriptions;
        private String proDate;
        private String ExpDate;
        private String place;
        private String storageType;
        private String type;
        private String image;
        private Long billId;

        private String supplierName;

        MainLabTubeStoreDtoBuilder() {
        }

        public MainLabTubeStoreDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MainLabTubeStoreDtoBuilder tubeName(String tubeName) {
            this.tubeName = tubeName;
            return this;
        }

        public MainLabTubeStoreDtoBuilder tubeCompany(String tubeCompany) {
            this.tubeCompany = tubeCompany;
            return this;
        }

        public MainLabTubeStoreDtoBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public MainLabTubeStoreDtoBuilder itemBuyingPrice(BigDecimal itemBuyingPrice) {
            this.itemBuyingPrice = itemBuyingPrice;
            return this;
        }

        public MainLabTubeStoreDtoBuilder barcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public MainLabTubeStoreDtoBuilder descriptions(String descriptions) {
            this.descriptions = descriptions;
            return this;
        }

        public MainLabTubeStoreDtoBuilder proDate(String proDate) {
            this.proDate = proDate;
            return this;
        }

        public MainLabTubeStoreDtoBuilder ExpDate(String ExpDate) {
            this.ExpDate = ExpDate;
            return this;
        }

        public MainLabTubeStoreDtoBuilder place(String place) {
            this.place = place;
            return this;
        }

        public MainLabTubeStoreDtoBuilder storageType(String storageType) {
            this.storageType = storageType;
            return this;
        }

        public MainLabTubeStoreDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public MainLabTubeStoreDtoBuilder image(String image) {
            this.image = image;
            return this;
        }

        public MainLabTubeStoreDtoBuilder billId(Long billId) {
            this.billId = billId;
            return this;
        }

        public MainLabTubeStoreDtoBuilder billId(String supplierName) {
            this.supplierName = supplierName;
            return this;
        }

        public MainLabTubeStoreDto build() {
            return new MainLabTubeStoreDto(this.id, this.tubeName, this.tubeCompany, this.quantity,
                    this.itemBuyingPrice, this.barcode, this.descriptions, this.proDate, this.ExpDate, this.place,
                    this.storageType, this.type, this.image, this.billId , this.supplierName);
        }

        public String toString() {
            return "MainLabTubeStoreDto.MainLabTubeStoreDtoBuilder(id=" + this.id + ", tubeName=" + this.tubeName + ", tubeCompany=" + this.tubeCompany + ", quantity=" + this.quantity + ", itemBuyingPrice=" + this.itemBuyingPrice + ", barcode=" + this.barcode + ", descriptions=" + this.descriptions + ", proDate=" + this.proDate + ", ExpDate=" + this.ExpDate + ", place=" + this.place + ", storageType=" + this.storageType + ", type=" + this.type + ", image=" + this.image + ", billId=" + this.billId + ")";
        }
    }


}
