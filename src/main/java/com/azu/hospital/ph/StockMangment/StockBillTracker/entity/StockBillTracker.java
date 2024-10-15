package com.azu.hospital.ph.StockMangment.StockBillTracker.entity;


import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.ph.StockMangment.StockBillTracker.StockBillTypeEnum;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "stock_bill_tracker")
@Getter
@Setter
public class StockBillTracker {
    @Id
    @SequenceGenerator(
            name = "stock_bill_tracker_sequence",
            sequenceName = "stock_bill_tracker_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_bill_tracker_sequence"
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    private StockBillTypeEnum billType;

    private Long billId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "stock_bill_tracker_bill",
            joinColumns = @JoinColumn(name = "stock_bill_tracker_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_bill_id")
    )
    private List<StockBill> bill;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "stock_bill_tracker_consumables",
            joinColumns = @JoinColumn(name = "stock_bill_tracker_id"),
            inverseJoinColumns = @JoinColumn(name = "consumables_id")
    )
    private List<Consumables> consumables;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "stock_bill_tracker_drugs",
            joinColumns = @JoinColumn(name = "stock_bill_tracker_id"),
            inverseJoinColumns = @JoinColumn(name = "drugs_id")
    )
    private List<DrugsItem> drugs;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "stock_bill_tracker_other_items",
            joinColumns = @JoinColumn(name = "stock_bill_tracker_id"),
            inverseJoinColumns = @JoinColumn(name = "other_items_id")
    )
    private List<OtherItems> otherItems;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "stock_bill_tracker_lab_items",
            joinColumns = @JoinColumn(name = "stock_bill_tracker_id"),
            inverseJoinColumns = @JoinColumn(name = "lab_items_id")
    )
    private List<MainLabTubeStore> labItems;


    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Instant createAt = Instant.now();
    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updateAt = Instant.now();


    public StockBillTracker() {
    }
    public StockBillTracker(StockBillTypeEnum billType) {
        this.billType = billType;
    }
}
