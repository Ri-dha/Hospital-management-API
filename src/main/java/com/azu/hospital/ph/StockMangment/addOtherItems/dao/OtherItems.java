package com.azu.hospital.ph.StockMangment.addOtherItems.dao;

import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.utils.enums.DeviceType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "otherItems"
)
public class OtherItems {

    @Id
    @SequenceGenerator(
            name = "item_id_seq",
            sequenceName = "item_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_id_seq"
    )
    private Long itemId;
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

    @ElementCollection( fetch = FetchType.EAGER)
    @Nullable
    private List<String> filesUrls = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    private String bonus;
    private String itemImageUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private StockBill bill;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "deviceDetails")
    private DeviceExistsTable deviceExistsTable;

    @Column(updatable = false)
    private Instant createAt;
    private Instant updateAt;



    public OtherItems() {
    }

    public OtherItems(Long itemId,
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
                      @Nullable List<String> filesUrls
    ) {
        this.itemId = itemId;
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
        this.bonus = bounce;
        this.itemImageUrl = itemImageUrl;
        this.filesUrls = filesUrls;
    }



    public OtherItems(
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
            String itemImageUrl,
            @Nullable List<String> filesUrls
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
        this.bonus = bounce;
        this.itemImageUrl = itemImageUrl;
        this.filesUrls = filesUrls;

    }

    public OtherItems(String itemName,
                      String itemCompany, Integer itemQuantity,
                      BigDecimal itemBuyingPrice, String itemBarcode,
                      String itemDescription, String serialNumber,
                      String itemProDate, String deviceWarrantyDate,
                      DeviceType deviceType, String bounce, String itemImageUrl,
                      @Nullable List<String> filesUrls, StockBill bill) {
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
        this.bonus = bounce;
        this.itemImageUrl = itemImageUrl;
        this.filesUrls = filesUrls;
        this.bill = bill;

    }

    public OtherItems(Long itemId, String itemName,
                      String itemCompany, Integer itemQuantity,
                      BigDecimal itemBuyingPrice, String itemBarcode,
                      String itemDescription, String serialNumber,
                      String itemProDate,String deviceWarrantyDate, DeviceType deviceType,String bounce, String itemImageUrl,
                      @Nullable List<String> filesUrls, StockBill bill) {
        this.itemId = itemId;
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
        this.bonus = bounce;
        this.itemImageUrl = itemImageUrl;
        this.filesUrls = filesUrls;
        this.bill = bill;
    }

    public OtherItems(String itemName, DeviceType deviceType) {
        this.itemName = itemName;
        this.deviceType = deviceType;
    }




    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updateAt = Instant.now();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCompany() {
        return itemCompany;
    }

    public void setItemCompany(String itemCompany) {
        this.itemCompany = itemCompany;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public BigDecimal getItemBuyingPrice() {
        return itemBuyingPrice;
    }

    public void setItemBuyingPrice(BigDecimal itemBuyingPrice) {
        this.itemBuyingPrice = itemBuyingPrice;
    }

    public String getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getItemProDate() {
        return itemProDate;
    }

    public void setItemProDate(String itemProDate) {
        this.itemProDate = itemProDate;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public StockBill getBill() {
        return bill;
    }

    public void setBill(StockBill bill) {
        this.bill = bill;
    }


    public DeviceExistsTable getDeviceExistsTable() {
        return deviceExistsTable;
    }

    public void setDeviceExistsTable(DeviceExistsTable deviceExistsTable) {
        this.deviceExistsTable = deviceExistsTable;
    }

    @Nullable
    public List<String> getFilesUrls() {
        return filesUrls;
    }

    public void setFilesUrls(@Nullable List<String> filesUrls) {
        this.filesUrls = filesUrls;
    }

    public String getDeviceWarrantyDate() {
        return deviceWarrantyDate;
    }

    public void setDeviceWarrantyDate(String deviceWarrantyDate) {
        this.deviceWarrantyDate = deviceWarrantyDate;
    }


    public String getBounce() {
        return bonus;
    }

    public void setBounce(String bounce) {
        this.bonus = bounce;
    }
}
