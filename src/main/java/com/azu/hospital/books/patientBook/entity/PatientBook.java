package com.azu.hospital.books.patientBook.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.books.patientBook.PatientBookType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "patient_books")
@Getter
@Setter
public class PatientBook {
    @Id
    @SequenceGenerator(
            name = "patient_book_id_seq",
            sequenceName = "patient_book_id_seq"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_book_id_seq")
    private Long id;


    @Enumerated(EnumType.STRING)
    private PatientBookType patientBookType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="base_user_id")
    private BaseUser doctor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id")
    private BaseUser nurse;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_manager_id")
    private BaseUser wardManger;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_manager_id")
    private BaseUser hospitalManager;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updateAt;


    public PatientBook() {
    }

    public PatientBook(PatientBookType patientBookType) {
        this.patientBookType = patientBookType;
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
