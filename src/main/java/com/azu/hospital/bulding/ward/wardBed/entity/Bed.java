package com.azu.hospital.bulding.ward.wardBed.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import com.azu.hospital.bulding.ward.entity.Ward;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Bed {

    @Id
    @SequenceGenerator(name = "bed_seq" , sequenceName = "bed_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String bedNumber;


    @ManyToOne(cascade = CascadeType.ALL ,  fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @OneToOne(cascade = CascadeType.ALL ,  fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL ,  fetch = FetchType.LAZY)
    @JoinColumn(name = "premature_baby_id")
    private PrematureBaby prematureBaby;

    private Boolean isBlock = false;

    private Boolean isDeleted = false;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public Bed() {
    }

    public Bed(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }


    public Boolean getIsBedReserved(){
        return patient != null || prematureBaby != null;
    };
}
