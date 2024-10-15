package com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.newPh.MedicinsRequests.entity.MedicinesRequests;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "medicines_requests_list")
@Getter
@Setter
public class MedicinesRequestsList {
    @Id
    @SequenceGenerator(
            name = "medicines_requests_list_id_seq",
            sequenceName = "medicines_requests_list_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medicines_requests_list_id_seq"
    )
    private Long id;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<MedicinesRequests> medicinesRequests;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "patient_id"
    )
    private Patient patient;


    @Column(updatable = false)
    private Instant createAt;
    @Column(insertable = false)
    private Instant updateAt;

    public MedicinesRequestsList() {
    }

    public MedicinesRequestsList(Instant createAt, Instant updateAt, List<MedicinesRequests> medicinesRequests, Patient patient) {
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.medicinesRequests = medicinesRequests;
        this.patient = patient;
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
