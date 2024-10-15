package com.azu.hospital.radiology.external.entity;


import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.radiology.external.ExternalRadiologyResult.entity.ExternalRadiologyResult;
import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
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
@Table(name = "external_radiology_tests")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ExternalRadiologyTest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "external_radiology_test_id" , name = "external_radiology_test_id")
    private Long id;

    @Embedded
    private RadiologyPatientData patientData;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Enumerated(EnumType.STRING)
    private RadiologyTypeEnum type;

    @Enumerated(EnumType.STRING)
    private RadiologyOrderState state;

    @OneToOne(cascade = CascadeType.ALL)
    private ExternalRadiologyResult externalRadiologyResult;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "radiology_bill_handler_id")
    private RadiologyBillHandler radiologyBillHandler;

    @Column(updatable = false)
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


    public ExternalRadiologyTest(RadiologyOrderState state) {
        this.state=state;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public ExternalRadiologyTest(
            String note,
            RadiologyTypeEnum type,
            RadiologyOrderState state
    ) {
        this.note = note;
        this.type = type;
        this.state=state;
    }

    public ExternalRadiologyTest() {
    }


}
