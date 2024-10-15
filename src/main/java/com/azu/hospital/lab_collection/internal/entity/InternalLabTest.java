package com.azu.hospital.lab_collection.internal.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "internal_lab_test")
@EntityListeners(AuditingEntityListener.class)
public class InternalLabTest {

    @Id
    @SequenceGenerator(name = "int_lab_test_id", sequenceName = "int_lab_test_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InternalLabRequestStatusEnum state;

    @Column(columnDefinition = "TEXT")
    private String note;

    private TestDestination labTestDestination;

    private Boolean isArchived = false;

    @Column(updatable = false , name="created_at")
    private Instant createdAt;

    private Instant updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "internalLabTest")
    private List<IntTestRequest> testRequests;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vpatient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploader_id")
    private BaseUser uploader;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "accepter_id")
    private BaseUser accepter;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rejecter_id")
    private BaseUser rejecter;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "consultant_patient_id")
    private ConsultantPatient consultantPatient;



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

    public InternalLabTest() {
    }

    public InternalLabTest(
            InternalLabRequestStatusEnum state,
            String note ,
            TestDestination labTestDestination

    ) {
        this.state = state;
        this.note = note;
        this.labTestDestination = labTestDestination;

    }


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }



    @PreUpdate
    public void updatedAt() {
        this.updatedAt = Instant.now();
    }
}
