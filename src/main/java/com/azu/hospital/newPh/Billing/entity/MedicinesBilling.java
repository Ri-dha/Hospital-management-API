package com.azu.hospital.newPh.Billing.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.newPh.Billing.BillingType;
import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity.MedicinesRequestsList;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "medicines_billing")
@Getter
@Setter
public class MedicinesBilling {
    @Id
    @SequenceGenerator(name = "medicines_billing_sequence", sequenceName = "medicines_billing_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicines_billing_sequence")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BillingType billingType;

    private String FinalPrice;

    private Long quantity;
    private Long totalItems;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "medicines_requests_list_id"
    )
    private MedicinesRequestsList medicinesRequestsList;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "patient_id"
    )
    @Nullable
    private Patient patient;

    @Column(updatable = false)
    private Instant createAt;
    @Column(insertable = false)
    private Instant updateAt;



    public MedicinesBilling() {
    }

    public MedicinesBilling(BillingType billingType, String finalPrice, MedicinesRequestsList medicinesRequestsList, Instant createAt, Instant updateAt) {
        this.billingType = billingType;
        FinalPrice = finalPrice;
        this.medicinesRequestsList = medicinesRequestsList;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    @PrePersist
    public void getCrate(){
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void getUpdate(){
        this.updateAt = Instant.now();
    }



}
