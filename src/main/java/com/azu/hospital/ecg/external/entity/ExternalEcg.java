package com.azu.hospital.ecg.external.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.ultrasound.external.entity.UltrasoundPatientData;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "external_ecgs")
@Data
public class ExternalEcg {

    @Id
    @SequenceGenerator(name = "external_ecg_id_seq" , sequenceName = "external_ecg_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Embedded
    private EcgPatientData patientData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    private EcgStates state;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<>();


    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public ExternalEcg() {
    }

    public ExternalEcg(EcgStates state ,EcgPatientData patientData) {
        this.state = state;
        this.patientData = patientData;
    }


}
