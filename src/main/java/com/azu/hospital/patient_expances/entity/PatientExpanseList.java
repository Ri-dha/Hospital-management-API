package com.azu.hospital.patient_expances.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "patient-expanse-list")
@Getter
@Setter
public class PatientExpanseList {

    @Id
    @SequenceGenerator(
            name = "patient-expanse-list_id_sequence",
            sequenceName = "patient-expanse-list_id_sequence",
            allocationSize = 55
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient-expanse-list_id"
    )
    private Long id;

    private Boolean isArchived = false;




    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private BaseUser user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_expanse_list_patient_expanse",
            joinColumns = @JoinColumn(name = "patient_expanse_list_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_expanse_id")
    )
    private List<PatientExpanse> patientExpanse;


    @Column(updatable = false)
    private Instant createdAt;
    private Instant updateAt;

    public PatientExpanseList() {
    }


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updateAt = Instant.now();
    }
}
