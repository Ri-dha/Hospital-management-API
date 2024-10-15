package com.azu.hospital.ecg.internal.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.ecg.ecgbill.entity.EcgBill;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ecgs")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Ecg {

    Boolean isArchived = false;
    @Id
    @SequenceGenerator(name = "ecg_id_seq", sequenceName = "ecg_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(columnDefinition = "TEXT")
    private String noteFromConsultant;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pateint_id")
    private Patient patient;
    @Enumerated(EnumType.STRING)
    private EcgStates state;
    @Enumerated(EnumType.STRING)
    private TestDestination testDestination;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "consultant_patient_id")
    private ConsultantPatient consultantPatient;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "srcUser_id")
    private BaseUser srcUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ecg_bill_id")
    private EcgBill ecgBill;
    @Column(updatable = false)
    private Instant createdAt;
    @Column(insertable = false)
    private Instant updatedAt;


    @CreatedBy
    @Column(
            updatable = false
    )
    private Long createdBy;
    @LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;


    public Ecg() {
    }

    public Ecg(String note, Patient patient, EcgStates state, EcgBill ecgBill) {
        this.note = note;
        this.patient = patient;
        this.state = state;
        this.ecgBill = ecgBill;
    }

    public Ecg(
            EcgStates state,
            TestDestination testDestination
    ) {
        this.state = state;
        this.testDestination = testDestination;
    }

    public Ecg(String note, EcgStates state, TestDestination testDestination) {
        this.note = note;
        this.state = state;
        this.testDestination = testDestination;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }


}
