package com.azu.hospital.accounting.add_priceses.entity;

import com.azu.hospital.utils.enums.EnumItemType;
import com.azu.hospital.utils.enums.patient.BillState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "prices")
@Data
public class Prices {
    @Id
    @SequenceGenerator(
            name = "prices_sequence_id",
            sequenceName = "prices_sequence_id",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Item Name required")
    private String itemName;
    private BigDecimal finalPrice;
    @Enumerated(EnumType.STRING)
    private EnumItemType type;
    private String note;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    private BillState billState;
    public Prices() {
    }

    public Prices(String itemName, BigDecimal finalPrice, EnumItemType type, String note) {
        this.itemName = itemName;
        this.finalPrice = finalPrice;
        this.type = type;
        this.note = note;
    }

    @PrePersist
    public void onCreate(){
        this.createdAt = Instant.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.updatedAt = Instant.now();
    }
}
