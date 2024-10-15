package com.azu.hospital.ultrasound.internal.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.entity.InternalUltrasoundResult;
import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "internal_ultrasound_tests")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class InternalUltrasoundTest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "internal_ultrasound_test_id" , name = "internal_ultrasound_test_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String note;


    @Enumerated(EnumType.STRING)
    private UltrasoundTypeEnum type;

    @Enumerated(EnumType.STRING)
    private UltrasoundOrderState state;

    @OneToOne(cascade = CascadeType.ALL)
    private InternalUltrasoundResult internalUltrasoundResult;

    private TestDestination testDestination;

    private Boolean isArchived = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "consultant_patient_id")
    private ConsultantPatient consultantPatient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse_id")
    private User nurse;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploader_id")
    private BaseUser uploader;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "accepter_id")
    private BaseUser accepter;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rejecter_id")
    private BaseUser rejecter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ultrasound_bill_id")
    private UltrasoundBill ultrasoundBill;


    @Column(updatable = false , name = "created_at")
    private Instant createdAt;

    @Column(insertable = false , name = "updated_at")
    private Instant updatedAt;


    @CreatedBy
    @Column(
            updatable = false
    )
    private Long createdBy;
    @org.springframework.data.annotation.LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public InternalUltrasoundTest(
            String note,
            UltrasoundTypeEnum type,
            UltrasoundOrderState state,
            TestDestination testDestination
    ) {
        this.note = note;
        this.type = type;
        this.state = state;
        this.testDestination = testDestination;
    }

    public InternalUltrasoundTest() {
    }


}
