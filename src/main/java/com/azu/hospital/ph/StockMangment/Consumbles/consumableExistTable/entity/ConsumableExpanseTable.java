package com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity;

import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "consumable_expanse_table")
@Getter
@Setter
public class ConsumableExpanseTable {
    @Id
    @SequenceGenerator(
            name = "consumable_expanse_sequence_id",
            sequenceName = "consumable_expanse_sequence_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    private Integer quantity;
    private String consumablePlace;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "consumable_id")
    private Consumables mainsConsumables;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "consumable_expanse_table_sold_items",
            joinColumns = @JoinColumn(name = "consumable_expanse_table_id"),
            inverseJoinColumns = @JoinColumn(name = "sold_item_id")
    )
    private List<SoldItems> soldItems;

    @Column(updatable = false)
    private Instant createAt;
    @Column(insertable = false)
    private Instant updateAt;

    public ConsumableExpanseTable() {
    }


    @PrePersist
    public void setCreatedAt() {
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updateAt = Instant.now();
    }


}
