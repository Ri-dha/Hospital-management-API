package com.azu.hospital.ultrasound.external.entity;


import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.entity.ExternalUltrasoundResult;
import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "external_ultrasound_tests")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ExternalUltrasoundTest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "external_ultrasound_test_id" , name = "external_ultrasound_test_id")
    private Long id;

    @Embedded
    private UltrasoundPatientData patientData;

    @Enumerated(EnumType.STRING)
    private UltrasoundTypeEnum type;

    @Column(columnDefinition = "TEXT")
    private String note;


    private UltrasoundOrderState state;

    @OneToOne(cascade = CascadeType.ALL)
    private ExternalUltrasoundResult externalUltrasoundResult;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse_id")
    private User nurse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ultrasound_bill_id")
    private UltrasoundBill ultrasoundBill;

    @Column(updatable = false , name = "created_at")
    private Instant createdAt;

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

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public ExternalUltrasoundTest(
            String note,
            UltrasoundTypeEnum type,
            UltrasoundOrderState state
    ) {
        this.note = note;
        this.type = type;
        this.state = state;
    }

    public ExternalUltrasoundTest() {
    }



   
}
