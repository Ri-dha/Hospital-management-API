package com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.entity;

import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import jakarta.persistence.*;

import java.time.Instant;

;


@Entity
@Table(name = "drug_expanse_table")

public class DrugExpanseTable {

    @Id
    @SequenceGenerator(
            name = "drug_expanse_table_id_sequence",
            sequenceName = "drug_expanse_table_id_sequence",
            allocationSize = 10
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_id")
    private DrugsItem drugsItem;

    private Integer quantity;

    private Instant updateAt;

    public DrugExpanseTable() {
    };

    public DrugExpanseTable(Integer quantity, Instant updateAt) {
        this.quantity = quantity;
        this.updateAt = updateAt;
    }

    @PreUpdate
    public void getUpdateAt(){
        this.updateAt = Instant.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public DrugsItem getDrugsItem() {
        return drugsItem;
    }

    public void setDrugsItem(DrugsItem drugsItem) {
        this.drugsItem = drugsItem;
    }


}
