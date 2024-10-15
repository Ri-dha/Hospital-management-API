package com.azu.hospital.bulding.unit.unitFurniture.entity;

import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class UnitFurniture {

    @Id
    @SequenceGenerator(
            name = "unit_furniture_seq",
            sequenceName = "unit_furniture_seq"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer count;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(columnDefinition = "TEXT")
    private String receivedNote;

    private UnitInventoryRequestEnum status;

    @ManyToOne
    @JoinColumn(name = "furniture_id")
    @JsonIgnore
    private OtherItems furniture;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    @JsonIgnore
    private Unit unit;

    private Instant createdAt;

    private Instant updatedAt;

    public UnitFurniture() {
    }

    public UnitFurniture(Integer count , UnitInventoryRequestEnum status , String note) {
        this.count = count;
        this.status =status;
        this.note = note;
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
