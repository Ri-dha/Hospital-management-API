package com.azu.hospital.operation.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.utils.enums.AnesthesiaType;
import com.azu.hospital.utils.enums.operation.OperationStates;
import com.azu.hospital.utils.enums.operation.OperationTriage;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "operation_seq_id" , sequenceName = "operation_seq_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @Column(columnDefinition = "TEXT")
    private String requestNote;


    @Column(columnDefinition = "TEXT")
    private String doneNote;

    private OperationStates state;


    private OperationTriage triage;

    private AnesthesiaType anesthetization;

    private Boolean isArchived = false;


    private Instant operationDate;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "surgical_id")
    private MainSurgicalList surgical;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    private BaseUser uploader ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accepter_id")
    private BaseUser accepter ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rejecter_id")
    private BaseUser rejecter ;


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


    public Operation(
            String requestNote,
            OperationStates state,
            AnesthesiaType anesthetization,
            Instant operationDate ,
            OperationTriage triage
    ) {
        this.requestNote = requestNote;
        this.state = state;
        this.anesthetization = anesthetization;
        this.operationDate = operationDate;
        this.triage = triage;
    }
}
