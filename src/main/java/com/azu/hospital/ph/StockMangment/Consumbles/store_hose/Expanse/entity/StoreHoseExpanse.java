package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.entity;

import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "store_hose_expanse")
@Data
public class StoreHoseExpanse {


    @Id
    @SequenceGenerator(name = "store_hose_expanse_id_seq" ,sequenceName ="store_hose_expanse_id_seq" )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_lab_tube_id")
    private MainLabTubeStore mainLabTubeStore;

    private Integer quantity;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;


    public StoreHoseExpanse() {
    }

    public StoreHoseExpanse(Integer quantity) {
        this.quantity = quantity;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }



}
