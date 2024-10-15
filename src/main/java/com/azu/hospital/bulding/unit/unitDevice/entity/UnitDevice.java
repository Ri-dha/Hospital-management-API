package com.azu.hospital.bulding.unit.unitDevice.entity;


import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;


@Entity
@Data
@Table(name = "unit_device")
public class UnitDevice {

    @Id
    @SequenceGenerator(
            name = "unit_device_seq",
            sequenceName = "unit_device_seq"
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
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private OtherItems device;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    @JsonIgnore
    private Unit unit;

    private Instant createdAt;

    private Instant updatedAt;

    public UnitDevice() {
    }

    public UnitDevice(Integer count , UnitInventoryRequestEnum status , String note) {
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
