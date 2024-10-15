package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "durg_request_handler_list_main")
public class DrugRequestHandlerList {

    @Id
    @SequenceGenerator(
            name = "durg_request_handler_list_id_seq",
            sequenceName = "durg_request_handler_list_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "durg_request_handler_list_id_seq"
    )
    private Long requestListId;

    private Integer acceptedCount = 0;
    private Integer rejectedCount = 0;


    @Column(updatable = false)
    private Instant createAt;
    @Column(insertable = false)
    private Instant updateAt;

    private boolean isCompleted = false;


    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<DrugRequestHandler> drugRequestHandlers;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public DrugRequestHandlerList() {
    }
    public DrugRequestHandlerList( Instant createAt, Instant updateAt, List<DrugRequestHandler> drugRequestHandlers, Patient patient) {
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.drugRequestHandlers = drugRequestHandlers;
        this.patient = patient;
    }


    @PrePersist
    public void getCrate(){
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void getUpdate(){
        this.updateAt = Instant.now();
    }



}
