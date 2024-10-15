package com.azu.hospital.radiology.internal.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.radiology.internal.InternalRadiologyResult.entity.InternalRadiologyResult;
import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.utils.enums.TestDestination;
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
@Table(name = "internal_radiology_tests")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class InternalRadiologyTest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "internal_radiology_test_id" , name = "internal_radiology_test_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Enumerated(EnumType.STRING)
    private RadiologyTypeEnum type;

    private RadiologyOrderState state;

    private TestDestination testDestination;

    private Boolean isArchived = false;

    @OneToOne(cascade = CascadeType.ALL)
    private InternalRadiologyResult internalRadiologyResult;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "consultant_patient_id")
    private ConsultantPatient consultantPatient;

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
    @JoinColumn(name = "radiology_bill_handler_id")
    private RadiologyBillHandler radiologyBillHandler;

    @Column(updatable = false)
    private Instant createdAt;
    @Column(insertable = false)
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


    public InternalRadiologyTest(RadiologyOrderState state){
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

    public InternalRadiologyTest(
            String note,
            RadiologyTypeEnum type,
            RadiologyOrderState state ,
            TestDestination testDestination
    ) {
        this.note = note;
        this.type = type;
        this.state = state;
        this.testDestination = testDestination;
    }

    public InternalRadiologyTest() {
    }


 
}
