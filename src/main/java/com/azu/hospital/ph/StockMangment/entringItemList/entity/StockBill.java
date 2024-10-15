package com.azu.hospital.ph.StockMangment.entringItemList.entity;


import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.utils.enums.StockBillState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
@Entity
@Table(name = "stock_bill")
@Getter
@Setter
public class StockBill {

    @Id
    @SequenceGenerator(
            name = "bill_id_seq",
            sequenceName = "bill_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bill_id_seq"
    )
    private Long billId;
    @NotNull(message = "Pharmacy Name Required")
    @NotBlank(message = "Pharmacy Name Required")
    @NotEmpty(message = "Pharmacy Name Required")
    private String pharmacyName;
    @NotNull(message = "Supplier Name Required")
    @NotBlank(message = "Supplier Name Required")
    @NotEmpty(message = "Supplier Name Required")
    private String supplierDetails;


    @NotNull(message = "Location Name Required")
    @NotBlank(message = "Location Name Required")
    @NotEmpty(message = "Location Name Required")
    private String location;


    private String mobile;

    @NotNull(message = "List Number Required")
    private BigDecimal billNumber;

    @NotNull(message = "List Date Required")
    @NotBlank(message = "List Date Required")
    @NotEmpty(message = "List Date Required")
    private String billDate;

    @NotNull(message = "List Entry Date Required")
    @NotBlank(message = "List Entry Date Required")
    @NotEmpty(message = "List Entry Date Required")
    private String billEntryDate;

    @NotNull(message = "Price Required")
    private BigDecimal billTotalPrice;

    private BigDecimal billTotalDiscount;

    private BigDecimal billTotalDebts;

    @NotNull(message = "Money Paid Required")
    private BigDecimal billTotalMoneyPaid;
    @NotNull(message = "Total Price After Discount Require ")
    private BigDecimal billTotalAfterDiscount;
    @NotNull(message = "Rest Money  Required")
    private BigDecimal totalRestMoney;

    private String billDescriptions;


    // TODO: 11/10/2023 Edit Pharmacy and Supplier to the privatise

//    @ManyToOne
//    @JoinColumn(name = "pharmacy_id")
//    private Pharmacy pharmacy;

    private String pharmacy;

//    @ManyToOne
//    @JoinColumn(name = "supplier_id")
//    private Supplier supplier;

    private String supplier;

    private StockBillState billState;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Instant createAt = Instant.now();
    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updateAt = Instant.now();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DrugsItem> drugs;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bill")
    private List<Consumables> consumables;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bill")
    private List<OtherItems> items;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "bill")
    private List<MainLabTubeStore> mainLabTubeStores;
    public StockBill() {
    }

    public StockBill(
            Long billId,
            String pharmacyName,
            String supplierDetails,
            BigDecimal billNumber,
            String billDate,
            String billEntryDate,
            BigDecimal billTotalPrice,
            BigDecimal billTotalDiscount,
            BigDecimal billTotalDebts,
            BigDecimal billTotalMoneyPaid,
            BigDecimal billTotalAfterDiscount,
            BigDecimal totalRestMoney,
            String billDescriptions,
            String location,
            String mobile,
            StockBillState billState
    ) {
        this.billId = billId;
        this.pharmacyName = pharmacyName;
        this.supplierDetails = supplierDetails;
        this.billNumber = billNumber;
        this.billDate = billDate;
        this.billEntryDate = billEntryDate;
        this.billTotalPrice = billTotalPrice;
        this.billTotalDiscount = billTotalDiscount;
        this.billTotalDebts = billTotalDebts;
        this.billTotalMoneyPaid = billTotalMoneyPaid;
        this.billTotalAfterDiscount = billTotalAfterDiscount;
        this.totalRestMoney = totalRestMoney;
        this.billDescriptions = billDescriptions;
        this.location = location;
        this.mobile = mobile;
        this.billState = billState;
    }

    public StockBill(
            String pharmacyName,
            String supplierDetails,
            BigDecimal billNumber,
            String billDate,
            String billEntryDate,
            BigDecimal billTotalPrice,
            BigDecimal billTotalDiscount,
            BigDecimal billTotalDebts,
            BigDecimal billTotalMoneyPaid,
            BigDecimal billTotalAfterDiscount,
            BigDecimal totalRestMoney,
            String billDescriptions,
            String location,
            String mobile
    ) {
        this.pharmacyName = pharmacyName;
        this.supplierDetails = supplierDetails;
        this.billNumber = billNumber;
        this.billDate = billDate;
        this.billEntryDate = billEntryDate;
        this.billTotalPrice = billTotalPrice;
        this.billTotalDiscount = billTotalDiscount;
        this.billTotalDebts = billTotalDebts;
        this.billTotalMoneyPaid = billTotalMoneyPaid;
        this.billTotalAfterDiscount = billTotalAfterDiscount;
        this.totalRestMoney = totalRestMoney;
        this.billDescriptions = billDescriptions;
        this.location = location;
        this.mobile = mobile;
    }

    public StockBill(
            Long billId,
            String pharmacyName,
            String supplierDetails,
            BigDecimal billNumber,
            String billDate,
            String billEntryDate,
            BigDecimal billTotalPrice,
            BigDecimal billTotalDiscount,
            BigDecimal billTotalDebts,
            BigDecimal billTotalMoneyPaid,
            BigDecimal billTotalAfterDiscount,
            BigDecimal totalRestMoney,
            String billDescriptions,
            String pharmacy,
            String supplier,
            String location,
            String mobile
    ) {
        this.billId = billId;
        this.pharmacyName = pharmacyName;
        this.supplierDetails = supplierDetails;
        this.billNumber = billNumber;
        this.billDate = billDate;
        this.billEntryDate = billEntryDate;
        this.billTotalPrice = billTotalPrice;
        this.billTotalDiscount = billTotalDiscount;
        this.billTotalDebts = billTotalDebts;
        this.billTotalMoneyPaid = billTotalMoneyPaid;
        this.billTotalAfterDiscount = billTotalAfterDiscount;
        this.totalRestMoney = totalRestMoney;
        this.billDescriptions = billDescriptions;
        this.pharmacy = pharmacy;
        this.supplier = supplier;
        this.location = location;
        this.mobile = mobile;
    }

    public StockBill(
            String pharmacyName,
            String supplierDetails,
            BigDecimal billNumber,
            String billDate,
            String billEntryDate,
            BigDecimal billTotalPrice,
            BigDecimal billTotalDiscount,
            BigDecimal billTotalDebts,
            BigDecimal billTotalMoneyPaid,
            BigDecimal billTotalAfterDiscount,
            BigDecimal totalRestMoney,
            String billDescriptions,
            String pharmacy,
            String supplier,
            String location,
            String mobile
    ) {
        this.pharmacyName = pharmacyName;
        this.supplierDetails = supplierDetails;
        this.billNumber = billNumber;
        this.billDate = billDate;
        this.billEntryDate = billEntryDate;
        this.billTotalPrice = billTotalPrice;
        this.billTotalDiscount = billTotalDiscount;
        this.billTotalDebts = billTotalDebts;
        this.billTotalMoneyPaid = billTotalMoneyPaid;
        this.billTotalAfterDiscount = billTotalAfterDiscount;
        this.totalRestMoney = totalRestMoney;
        this.billDescriptions = billDescriptions;
        this.pharmacy = pharmacy;
        this.supplier = supplier;
        this.location = location;
        this.mobile = mobile;
    }



}
