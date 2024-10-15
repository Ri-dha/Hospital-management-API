package com.azu.hospital.ph.StockMangment.Consumbles.entity;




import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity.ConsumableExpanseTable;

import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;


@Entity
@Table(
        name = "consumables"
)
@Getter
@Setter
public class Consumables {
    @Id
    @SequenceGenerator(
            name = "consumables_id_seq",
            sequenceName = "consumable_id_seq",
            allocationSize = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "consumable_id_seq"
    )
    private Long consumableId;

    @Column(
            nullable = false
    )
    @NotNull(
            message = "Consumable name should not be null"
    )
    @Size(
            min = 4,
            message = "Items Name should be less than 50 char and more than 2 char"
    )
    private String consumableName;
    @NotNull(
            message = "Consumable Company name should not be null"
    )
    private String consumableCompany;
    @Column(
            nullable = false
    )
    @NotNull(
            message = "Quantity should not be null"
    )
    private Integer quantity;
    @Column(
            nullable = false
    )
    @NotNull(
            message = "Price should not be null"
    )
    private BigDecimal price;
    private BigDecimal consumableSellingPrice;
    @Column(
            nullable = false
    )
    @NotNull(
            message = "Barcode Should Not be Null"
    )
    @Size(
            min = 2,
            message = "Barcode should be less than 50 char and more than 2 char"
    )
    private String barcode;
    @Column(
            nullable = false
    )
    @NotNull(
            message = "Expiry date should not be null"
    )
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Expiry date should be in the format YYYY-MM-DD"
    )
    @DateTimeFormat(
            style = "Expiry date should be in the format YYYY-MM-DD"
    )
    private String expDate;

    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Production date should be in the format YYYY-MM-DD"
    )
    @DateTimeFormat(
            style = "Product date should be in the format YYYY-MM-DD"
    )
    private String proDate;
    @NotNull(message = "Type of Currency Require")

    private Currency typeOfCurrency; // USD or IQD
    @NotNull(
            message = "Money exchange Required"
    )
    private BigDecimal exchange; // the changes between USD to IQD
    @Column(
            columnDefinition = "TEXT"
    )
    private String description;
    private String bounce;
    private String consumablePharmacyPlace;
    private String consumableImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private StockBill bill;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "consumables_sold_items",
            joinColumns = @JoinColumn(name = "consumable_id"),
            inverseJoinColumns = @JoinColumn(name = "sold_item_id")
    )
    private List<SoldItems> soldItems;

    @Column(updatable = false)
    private Instant createAt;
    @Column(insertable = false)
    private Instant updateAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ConsumableExpanseTable existsTable;




    public Consumables(
            Long consumableId,
            String consumableName,
            String consumableCompany,
            Integer quantity,
            BigDecimal price,
            BigDecimal consumableSellingPrice,
            String barcode,
            String expDate,
            String proDate,
            Currency typeOfCurrency,
            BigDecimal exchange,
            String description,
            String bounce,
            String consumablePharmacyPlace,
            String consumableImage
    ) {
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
        this.consumablePharmacyPlace = consumablePharmacyPlace;
        this.consumableImage = consumableImage;
    }

    public Consumables(String consumableName, String consumableCompany,
                       Integer quantity, BigDecimal price, BigDecimal consumableSellingPrice,
                       String barcode, String expDate, String proDate, Currency typeOfCurrency,
                       BigDecimal exchange, String description, String bounce, String consumablePharmacyPlace, String consumableImage) {
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
        this.consumablePharmacyPlace = consumablePharmacyPlace;
        this.consumableImage = consumableImage;
    }

    public Consumables() {

    }

    public void setSoldItems(List<SoldItems> soldItems) {
        this.soldItems = soldItems;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public void setExistsTable(ConsumableExpanseTable existsTable) {
        this.existsTable = existsTable;
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

    public void setBill(StockBill bill) {
        this.bill = bill;
    }

    public void setConsumableId(Long consumableId) {
        this.consumableId = consumableId;
    }

    public void setConsumableName(String consumableName) {
        this.consumableName = consumableName;
    }

    public void setConsumableCompany(String consumableCompany) {
        this.consumableCompany = consumableCompany;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setProDate(String proDate) {
        this.proDate = proDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConsumableImage(String consumableImage) {
        this.consumableImage = consumableImage;
    }

    public void setTypeOfCurrency(Currency typeOfCurrency) {
        this.typeOfCurrency = typeOfCurrency;
    }

    public void setExchange(BigDecimal exchange) {
        this.exchange = exchange;
    }

    public void setConsumableSellingPrice(BigDecimal consumableSellingPrice) {
        this.consumableSellingPrice = consumableSellingPrice;
    }

    public void setConsumablePharmacyPlace(String consumablePharmacyPlace) {
        this.consumablePharmacyPlace = consumablePharmacyPlace;
    }

    public void setBounce(String bounce) {
        this.bounce = bounce;
    }
}
