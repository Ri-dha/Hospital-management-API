package com.azu.hospital.patient_expances.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "patient_expances")
@Getter
@Setter
public class PatientExpanse {

    @Id
    @SequenceGenerator(
            name = "patient_exp_id_sequence",
            sequenceName = "patient_exp_id_sequence",
            allocationSize = 55
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_exp_id"
    )
    private Long id;

    @NotEmpty(message = "Item name Required")
    @NotNull(message = "Item name Required")
    @NotBlank(message = "Item name Required")
    private String itemName;
    @NotNull(message = "Item count Required")
    private Integer itemCount;

    private Boolean isArchived = false;

    private String note;

    @Column(updatable = false)
    private Instant createdAt;
    @Column(insertable = false)
    private Instant updateAt;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patientExpanse")
    private List<PatientExpanseList> patientExpanseLists;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "consumable_id")
    private Consumables consumables;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Patient patient;


    public PatientExpanse() {
    }

    public PatientExpanse(String itemName, Integer itemCount, String not) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.note = not;
    }



    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updateAt = Instant.now();
    }

    public void getConsumables(Consumables consumables) {
        this.consumables = consumables;
    }
}
