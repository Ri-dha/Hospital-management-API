package com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.entity.CanceledBottles;
import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class BottleOrder {
    @Id
    @SequenceGenerator(name = "bottle_order_id_seq" , sequenceName = "bottle_order_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private BottleTypeEnum bottleType;

    private BloodGroupEnum bloodGroup;

    private Integer count;

    private Integer time;

    private String receivedNote;

    private Boolean isReserved = false;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Enumerated
    private BottleStateEnum state;

    @Column(updatable = false)
    private Instant createdAt;


    private Instant updatedAt;


    @OneToMany(mappedBy = "bottleOrder", cascade = CascadeType.ALL)
    private List<Bottle> bottles;


    @OneToMany(mappedBy = "bottleOrder", cascade = CascadeType.ALL)
    @OrderBy("bottleType ASC")
    private List<CanceledBottles> canceledBottles;

    public BottleOrder(Boolean reserved, String note, BottleStateEnum bottleStateEnum, BottleTypeEnum bottleTypeEnum, BloodGroupEnum bloodGroupEnum, Integer count) {
        this.isReserved = reserved;
        this.note = note;
        this.state = bottleStateEnum;
        this.bottleType = bottleTypeEnum;
        this.bloodGroup = bloodGroupEnum;
        this.count = count;
    }


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public BottleOrder() {
    }


}
