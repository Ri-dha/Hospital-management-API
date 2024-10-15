package com.azu.hospital.ph.lab_inventory.lab_main_table.entity;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.entity.StoreHoseExpanse;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.utils.enums.LabTubeStorageType;
import com.azu.hospital.utils.enums.storeHose.StoreHoseItemEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Entity
@Table(name = "lab_inventory_storage")
@Getter
@Setter
public class MainLabTubeStore {

    @Id
    @SequenceGenerator(
            name = "lab_tube_id_seq",
            sequenceName = "lab_tube_id_seq",
            allocationSize = 55
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lab_tube_id_seq"
    )
    private Long id;
    @NotEmpty(message = "Item name Should not be null")
    @NotNull(message = "Item name Should not be null")
    @NotBlank(message = "Item name Should not be null")
    @Size(min = 4, max = 100, message = "Item name Should not be not less than 4 character")
    private String itemTubeName;
    @NotEmpty(message = "Item name company Should not be null")
    @NotNull(message = "Item name company Should not be null")
    @NotBlank(message = "Item name company Should not be null")
    @Size(min = 4, max = 100, message = "Item name company Should not be not less than 4 character")
    private String itemTubeCompany;
    @NotNull(message = "Item Quantity Should not be null")
    private Integer itemTubeQuantity;

    @NotNull(message = "Item Prise Should not be null")
//    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Price must be a valid number")
    @NumberFormat
    private BigDecimal itemBuyingPrice;

    @NotEmpty(message = "Item Barcode Should not be null")
    @NotNull(message = "Item Barcode Should not be null")
    @NotBlank(message = "Item Barcode Should not be null")
    private String itemTubeBarcode;
    private String itemTubeDescription;
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Product date should be in the format YYYY-MM-DD"
    )
    @DateTimeFormat(
            style = "Product date should be in the format YYYY-MM-DD"
    )
    private String itemTubeProDate;
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Expiry date should be in the format YYYY-MM-DD"
    )
    @DateTimeFormat(
            style = "Expiry date should be in the format YYYY-MM-DD"
    )
    @NotNull(message = "Expiry Date Require")
    @NotBlank(message = "Expiry Date Require")
    @NotEmpty(message = "Expiry Date Require")
    private String itemTubeExpDate;
    private String itemTubePlaceInStore;
    @NotNull(message = "storage Type required")
    private LabTubeStorageType storageType;
    @NotNull(message = "Tube Type required")
    private StoreHoseItemEnum tubeType;

    private String itemImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private StockBill bill;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "mainLabTubeStore", cascade = CascadeType.ALL)
    private StoreHoseExpanse storeHoseExpanse;
    public MainLabTubeStore() {
    };


    public MainLabTubeStore(Builder builder) {
        this.id = builder.mainLabTubeStore.getId();
        this.itemTubeName = builder.mainLabTubeStore.itemTubeName;
        this.itemTubeCompany = builder.mainLabTubeStore.itemTubeCompany;
        this.itemTubeQuantity = builder.mainLabTubeStore.itemTubeQuantity;
        this.itemBuyingPrice = builder.mainLabTubeStore.itemBuyingPrice;
        this.itemTubeBarcode = builder.mainLabTubeStore.itemTubeBarcode;
        this.itemTubeDescription = builder.mainLabTubeStore.itemTubeDescription;
        this.itemTubeProDate = builder.mainLabTubeStore.itemTubeProDate;
        this.itemTubeExpDate = builder.mainLabTubeStore.itemTubeExpDate;
        this.itemTubePlaceInStore = builder.mainLabTubeStore.itemTubePlaceInStore;
        this.storageType = builder.mainLabTubeStore.storageType;
        this.tubeType = builder.mainLabTubeStore.tubeType;
        this.itemImageUrl = builder.mainLabTubeStore.itemImageUrl;
        this.bill = builder.mainLabTubeStore.bill;
    }

    public static class Builder {
        private final MainLabTubeStore mainLabTubeStore;

        public Builder() {
            mainLabTubeStore = new MainLabTubeStore();
        }

        public Builder withId(Long id) {
            mainLabTubeStore.id = id;
            return this;
        }

        public Builder withItemTubeName(String itemTubeName) {
            mainLabTubeStore.itemTubeName = itemTubeName;
            return this;
        }
        public Builder withItemTubeCompany(String itemTubeCompany) {
            mainLabTubeStore.itemTubeCompany = itemTubeCompany;
            return this;
        }
        public Builder withItemTubeQuantity(Integer itemTubeQuantity) {
            mainLabTubeStore.itemTubeQuantity = itemTubeQuantity;
            return this;
        }
        public Builder withItemBuyingPrice(BigDecimal itemBuyingPrice) {
            mainLabTubeStore.itemBuyingPrice = itemBuyingPrice;
            return this;
        }
        public Builder withItemTubeBarcode(String itemTubeBarcode) {
            mainLabTubeStore.itemTubeBarcode = itemTubeBarcode;
            return this;
        }
        public Builder withItemTubeDescription(String itemTubeDescription) {
            mainLabTubeStore.itemTubeDescription = itemTubeDescription;
            return this;
        }
        public Builder withItemTubeProDate(String itemTubeProDate) {
            mainLabTubeStore.itemTubeProDate = itemTubeProDate;
            return this;
        }

        public Builder withItemTubeExpDate(String itemTubeExpDate) {
            mainLabTubeStore.itemTubeExpDate = itemTubeExpDate;
            return this;
        }
        public Builder withItemTubePlaceInStore(String itemTubePlaceInStore) {
            mainLabTubeStore.itemTubePlaceInStore = itemTubePlaceInStore;
            return this;
        }

        public Builder withStorageType(LabTubeStorageType storageType) {
            mainLabTubeStore.storageType = storageType;
            return this;
        }

        public Builder withTubeType(StoreHoseItemEnum tubeType) {
            mainLabTubeStore.tubeType = tubeType;
            return this;
        }
        public Builder withItemImageUrl(String itemImageUrl) {
            mainLabTubeStore.itemImageUrl = itemImageUrl;
            return this;
        }


        public MainLabTubeStore build() {

            return new MainLabTubeStore(this);
        }
    }

}
