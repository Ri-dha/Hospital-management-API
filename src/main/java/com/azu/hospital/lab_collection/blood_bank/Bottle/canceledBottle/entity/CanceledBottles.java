package com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.entity;

import com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity.BottleOrder;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table
@Data
public class CanceledBottles {

    @Id
    @SequenceGenerator(name = "canceled_bottles_seq" , sequenceName = "canceled_bottles_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private BottleTypeEnum bottleType;

    private BloodGroupEnum bloodGroup;

    private String bottleNumber;

    private String donatorName;

    private String donatorPhoneNumber;

    private String fillDate;

    private String expiredDate;

    @Column(columnDefinition = "text")
    private String note;

    @Column(insertable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "bottle_order_id")
    @JsonIgnore
    private BottleOrder bottleOrder;

    public CanceledBottles(
            BottleTypeEnum bottleType,
            BloodGroupEnum bloodGroup,
            String bottleNumber,
            String donatorName,
            String donatorPhoneNumber,
            String fillDate,
            String expiredDate,
            Instant createdAt
    ) {
        this.bottleType = bottleType;
        this.bloodGroup = bloodGroup;
        this.bottleNumber = bottleNumber;
        this.donatorName = donatorName;
        this.donatorPhoneNumber = donatorPhoneNumber;
        this.fillDate = fillDate;
        this.expiredDate = expiredDate;
        this.createdAt = createdAt;
    }

    public CanceledBottles() {

    };


}
