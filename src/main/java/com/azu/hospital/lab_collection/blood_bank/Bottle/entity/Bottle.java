package com.azu.hospital.lab_collection.blood_bank.Bottle.entity;

import com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity.BottleOrder;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "bottle")
public class Bottle {

    @Id
    @SequenceGenerator(name = "bottles_id_seq" , sequenceName = "bottles_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private BottleTypeEnum bottleType;

    private BloodGroupEnum bloodGroup;

//    @Column(unique = true)
    private String bottleNumber;

    private String donatorName;

    private String donatorPhoneNumber;

    private String fillDate;

    private String expiredDate;

    private BottleStateEnum state;

    @Column(columnDefinition = "text")
    private String note;

    private Instant createdAt;

    @Column(updatable = false)
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bottle_order_id")
    private BottleOrder bottleOrder;


    public Bottle(BottleTypeEnum bottleType, BloodGroupEnum bloodGroup, String bottleNumber, String donatorName, String donatorPhoneNumber, String fillDate, String expiredDate, BottleStateEnum bottleStateEnum, String note) {
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public Bottle() {
    }

    public Bottle(
            BottleTypeEnum bottleType,
            BloodGroupEnum bloodGroup,
            String bottleNumber,
            String donatorName,
            String donatorPhoneNumber,
            String fillDate,
            String expiredDate,
            BottleStateEnum state
    ) {
        this.bottleType = bottleType;
        this.bloodGroup = bloodGroup;
        this.bottleNumber = bottleNumber;
        this.donatorName = donatorName;
        this.donatorPhoneNumber = donatorPhoneNumber;
        this.fillDate = fillDate;
        this.expiredDate = expiredDate;
        this.state = state;
    }

    @AssertTrue(message = "bloodGroup must be null if bottleType is not BLOOD or RBC")
    public boolean isBloodGroupValid() {
        if (bottleType != BottleTypeEnum.BLOOD && bottleType != BottleTypeEnum.RBC) {
            return bloodGroup == null;
        }
        return true;
    }
}
