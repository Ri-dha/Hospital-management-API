package com.azu.hospital.newPh.Medicins.entity;

import com.azu.hospital.newPh.MedicinsRequests.entity.MedicinesRequests;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "medicines")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class Medicines {
    @Id
    @SequenceGenerator(name = "medicines_sequence", sequenceName = "medicines_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicines_sequence")
    private Long id;

    private String name;
    private String barCode;
    private Long price;
    private Long quantity;

    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updateAt;


    @OneToMany(mappedBy = "medicines", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicinesRequests> medicinesRequests;


    public Medicines() {
    }

    public Medicines(String name, String barCode, Long price, Long quantity) {
        this.name = name;
        this.barCode = barCode;
        this.price = price;
        this.quantity = quantity;
    }




}
