package com.azu.hospital.newPh.MedicinsRequests.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.newPh.Medicins.entity.Medicines;
import com.azu.hospital.ph.mediciens.type.MedicineType;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "medicines_requests")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class MedicinesRequests {
    @Id
    @SequenceGenerator(name = "medicines_requests_id_seq", sequenceName = "medicines_requests_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicines_requests_id_seq")
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "medicines_requests_medicines",
            joinColumns = @JoinColumn(name = "medicines_requests_id"),
            inverseJoinColumns = @JoinColumn(name = "medicines_id")
    )
    private Medicines medicines;

    @Enumerated(EnumType.STRING)
    private MedicineType type;

    @Enumerated(EnumType.STRING)
    private UnitInventoryRequestEnum requestStatus;

    @NotNull(message = "timesDay required")
    private String timesDay;

    @NotNull(message = "timesServing")
    private String timesServing;

    @NotNull(message = "does required")
    private String does;

    @NotNull(message = "quantity required")
    private Long quantity;

    @NotNull(message = "Route of AdminStriations required")
    private String roa;

    private String note;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Patient patient;


    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updateAt;


}
