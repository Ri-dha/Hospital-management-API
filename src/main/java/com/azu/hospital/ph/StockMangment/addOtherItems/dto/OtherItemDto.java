package com.azu.hospital.ph.StockMangment.addOtherItems.dto;

import com.azu.hospital.utils.enums.DeviceType;
import com.azu.hospital.utils.files.File;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OtherItemDto(
        Long itemId,
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
        String itemImageUrl ,
        List<String> files,
        Long billId,
        String supplierName,
        String inventoryPlace,
        Instant updateAt
) {
    public static OtherItemDtoBuilder builder() {
        return new OtherItemDtoBuilder();
    }

    public static class OtherItemDtoBuilder {
        private Long itemId;
        private String itemName;
        private String itemCompany;
        private Integer itemQuantity;
        private BigDecimal itemBuyingPrice;
        private String itemBarcode;
        private String itemDescription;
        private String serialNumber;
        private String itemProDate;
        private String deviceWarrantyDate;
        private DeviceType deviceType;
        private String bounce;
        private String itemImageUrl;
        private List<String> files;
        private Long billId;
        private String supplierName;
        private String inventoryPlace;
        private Instant updateAt;

        OtherItemDtoBuilder() {
        }

        public OtherItemDtoBuilder itemId(Long itemId) {
            this.itemId = itemId;
            return this;
        }

        public OtherItemDtoBuilder itemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public OtherItemDtoBuilder itemCompany(String itemCompany) {
            this.itemCompany = itemCompany;
            return this;
        }

        public OtherItemDtoBuilder itemQuantity(Integer itemQuantity) {
            this.itemQuantity = itemQuantity;
            return this;
        }

        public OtherItemDtoBuilder itemBuyingPrice(BigDecimal itemBuyingPrice) {
            this.itemBuyingPrice = itemBuyingPrice;
            return this;
        }

        public OtherItemDtoBuilder itemBarcode(String itemBarcode) {
            this.itemBarcode = itemBarcode;
            return this;
        }

        public OtherItemDtoBuilder itemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
            return this;
        }

        public OtherItemDtoBuilder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public OtherItemDtoBuilder itemProDate(String itemProDate) {
            this.itemProDate = itemProDate;
            return this;
        }

        public OtherItemDtoBuilder  deviceWarrantyDate(String deviceWarrantyDate) {
            this.deviceWarrantyDate = deviceWarrantyDate;
            return this;
        }

        public OtherItemDtoBuilder deviceType(DeviceType deviceType) {
            this.deviceType = deviceType;
            return this;
        }

        public OtherItemDtoBuilder bounce(String bounce ) {
            this.bounce = bounce;
            return this;
        }

        public OtherItemDtoBuilder itemImageUrl(String itemImageUrl) {
            this.itemImageUrl = itemImageUrl;
            return this;
        }

        public OtherItemDtoBuilder files(List<String> files) {
            this.files = files;
            return this;
        }

        public OtherItemDtoBuilder billId(Long billId) {
            this.billId = billId;
            return this;
        }
        public OtherItemDtoBuilder supplierName(String supplierName) {
            this.supplierName = supplierName;
            return this;
        }
        public OtherItemDtoBuilder inventoryPlace(String inventoryPlace) {
            this.inventoryPlace = inventoryPlace;
            return this;
        }
        public OtherItemDtoBuilder updateAt(Instant updateAt) {
            this.updateAt = updateAt;
            return this;
        }


        public OtherItemDto build() {
            return new OtherItemDto(this.itemId, this.itemName, this.itemCompany, this.itemQuantity,
                    this.itemBuyingPrice, this.itemBarcode, this.itemDescription, this.serialNumber, this.itemProDate, this.deviceWarrantyDate
                    , this.deviceType, this.bounce, this.itemImageUrl , this.files, this.billId, this.supplierName, this.inventoryPlace, this.updateAt);
        }


        public String toString() {
            return "OtherItemDtoBuilder{" +
                    "itemId=" + itemId +
                    ", itemName='" + itemName + '\'' +
                    ", itemCompany='" + itemCompany + '\'' +
                    ", itemQuantity=" + itemQuantity +
                    ", itemBuyingPrice=" + itemBuyingPrice +
                    ", itemBarcode='" + itemBarcode + '\'' +
                    ", itemDescription='" + itemDescription + '\'' +
                    ", serialNumber='" + serialNumber + '\'' +
                    ", itemProDate='" + itemProDate + '\'' +
                    ", deviceWarrantyDate='" +  deviceWarrantyDate + '\'' +
                    ", deviceType=" + deviceType +
                    ", bounce=" + bounce +
                    ", itemImageUrl='" + itemImageUrl + '\'' +
                    ", files=" + files + '\'' +
                    ",billId=" + billId + '\'' +
                    ", supplierName=" + supplierName + '\'' +
                    ", inventoryPlace=" + inventoryPlace + '\'' +
                    '}';
        }
    }
}
