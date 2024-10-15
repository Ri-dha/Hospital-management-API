package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.entity;

import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import com.azu.hospital.utils.enums.storeHose.StoreHoseItemEnum;
import com.azu.hospital.utils.enums.storeHose.StoreHoseStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class StoreHose {


    @Id
    @SequenceGenerator(name = "store_hose_id_seq" , sequenceName = "store_hose_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private MainLabTubeStore item;

    private Integer count;


    private String note;

    private String receivedNote;

    private StoreHoseStatusEnum state;

    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }


    public StoreHose() {
    }

    public StoreHose(
            Integer count,
            String note,
            StoreHoseStatusEnum state
    ) {
        this.count = count;
        this.note = note;
        this.state = state;
    }
}
