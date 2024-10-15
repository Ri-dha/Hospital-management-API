package com.azu.hospital.ph.main_data_store.drugs_item.entity;

import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.main_data_store.item_archive.entity.DrugItemArchive;
import com.azu.hospital.ph.mediciens.type.MedicineType;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;


@Entity
@Table(name = "drug_item")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class DrugsItem {

    @Id
    @SequenceGenerator(
            name = "base_item_id_seq",
            sequenceName = "base_item_id_seq",
            allocationSize = 60
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String itemName;
    private String itemCompany;


    private String expDate;

    private String proDate;
    private String description;

    private String barcode;

    @Nullable
    private String imageUrls;
    private BigDecimal exchange;
    private Currency typeOfCurrency;
    private Integer quantity;

    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updateAt;


    private String drugScientificName;


    private String dose;


    private String type;
    private String drugBonus;
    private String ndcNumber;

    private BigDecimal price ;
    private BigDecimal drugSellingPrice;

    private Boolean isBack = false;

    private Boolean isDrugExist = true; // TODO: 12/21/2023 this for check is drug availible



    @OneToOne(fetch = FetchType.LAZY, mappedBy = "drugsItem")
    private DrugItemArchive drugItemArchive;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drugsItem", cascade = CascadeType.ALL)
//    private List<DrugExpanseTable> expanseTable;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drugsItems")
    private List<DrugRequestHandler> drugRequestHandler;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "drugs_sold_items",
            joinColumns = @JoinColumn(name = "drug_id"),
            inverseJoinColumns = @JoinColumn(name = "sold_item_id")
    )
    private List<SoldItems> soldItems;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private StockBill bill;



    public DrugsItem() {
    }

    public DrugsItem(Long id, String itemName, String itemCompany, String expDate, String proDate,
                     String description, String barcode, @Nullable String imageUrls, BigDecimal exchange, Currency typeOfCurrency,
                     Integer quantity, Instant createdAt, Instant updateAt, String drugScientificName,
                     String dose, String type, String drugBonus, String ndcNumber,
                     BigDecimal price, BigDecimal drugSellingPrice, Boolean isBack, Boolean isDrugExist) {
        this.id = id;
        this.itemName = itemName;
        this.itemCompany = itemCompany;
        this.expDate = expDate;
        this.proDate = proDate;
        this.description = description;
        this.barcode = barcode;
        this.imageUrls = imageUrls;
        this.exchange = exchange;
        this.typeOfCurrency = typeOfCurrency;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.drugScientificName = drugScientificName;
        this.dose = dose;
        this.type = type;
        this.drugBonus = drugBonus;
        this.ndcNumber = ndcNumber;
        this.price = price;
        this.drugSellingPrice = drugSellingPrice;
        this.isBack = isBack;
        this.isDrugExist = isDrugExist;
    }

    public DrugsItem(String itemName, String itemCompany, String expDate, String proDate,
                     String description, String barcode, @Nullable String imageUrls, BigDecimal exchange,
                     Currency typeOfCurrency, Integer quantity, Instant createdAt, Instant updateAt,
                     String drugScientificName, String dose, String type, String drugBonus,
                     String ndcNumber, BigDecimal price, BigDecimal drugSellingPrice,
                     Boolean isBack, Boolean isDrugExist) {
        this.itemName = itemName;
        this.itemCompany = itemCompany;
        this.expDate = expDate;
        this.proDate = proDate;
        this.description = description;
        this.barcode = barcode;
        this.imageUrls = imageUrls;
        this.exchange = exchange;
        this.typeOfCurrency = typeOfCurrency;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.drugScientificName = drugScientificName;
        this.dose = dose;
        this.type = type;
        this.drugBonus = drugBonus;
        this.ndcNumber = ndcNumber;
        this.price = price;
        this.drugSellingPrice = drugSellingPrice;
        this.isBack = isBack;
        this.isDrugExist = isDrugExist;
    }

    public static DrugItemBuilder builder() {
        return new DrugItemBuilder();
    }



    public DrugRequestHandler getFirstDrugRequestHandler() {
        return drugRequestHandler.isEmpty() ? null : drugRequestHandler.get(0);
    }




}
