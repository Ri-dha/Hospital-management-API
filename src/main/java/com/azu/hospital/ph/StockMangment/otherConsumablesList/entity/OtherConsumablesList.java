package com.azu.hospital.ph.StockMangment.otherConsumablesList.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Table(name = "other_consumables_list")
@Entity
@Getter
@Setter
public class OtherConsumablesList {

    @Id
    @SequenceGenerator(
            name = "other_consumables_list_id_seq",
            sequenceName = "other_consumables_list_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "other_consumables_list_id_seq"
    )
    private Long listId;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id")
    private List<OtherConsumables> otherConsumables;


    @Column(updatable = false)
    private Instant createAt;
    private Instant updateAt;


    public OtherConsumablesList() {
    }

    public OtherConsumablesList(Long listId, Patient patient, List<OtherConsumables> otherConsumables, Instant createAt, Instant updateAt) {
        this.listId = listId;
        this.patient = patient;
        this.otherConsumables = otherConsumables;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updateAt = Instant.now();
    }


}
