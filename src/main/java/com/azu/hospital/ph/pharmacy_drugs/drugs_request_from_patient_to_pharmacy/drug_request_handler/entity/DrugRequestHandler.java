package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.ph.mediciens.type.MedicineType;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.entity.DrugExpanseTable;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "drug_request")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DrugRequestHandler {

    @Id
    @SequenceGenerator(
            name = "drug_request_id_seq",
            sequenceName = "drug_request_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "drug_request_id_seq"
    )
    private Long requestId;

    private Long drugId;

    @Column(columnDefinition = "TEXT")
    private String drugName;


    private Integer quantity;
    @NotNull(message = "requestStatus required")
    @Enumerated(EnumType.STRING)
    private UnitInventoryRequestEnum requestStatus;


    private String timesDay;

    private String timesServing;

    private String does;

    private String roa;


    @Enumerated(EnumType.STRING)
    private MedicineType type;

    private Boolean isArchived = false;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DrugExpanseTable> expanseTable;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "drug_request_exists_table",
            joinColumns = @JoinColumn(name = "drug_request_handler_request_id"),
            inverseJoinColumns = @JoinColumn(name = "exists_table_exist_drugs_in_store_id")
    )
    private DrugsItem drugsItems;

    @Column(columnDefinition = "TEXT")
    private String rejectCause;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private BaseUser signature;

    private String note;

    private Boolean isDrugDeleted = false;

    private Boolean isDrugStopped = false;

    @Transient
    @Column(columnDefinition = "TEXT")
    private String updateCrashed;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> crashUpdate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<DrugRequestHandlerList> drugRequestHandlerListsALL;



    @Column(updatable = false)
    private Instant createAt;

    private Instant updateAt;


//    @CreatedBy
//    @Column(
//            nullable = false,
//            updatable = false
//    )
//    private Long createdBy;
//    @LastModifiedBy
//    @Column(
//            insertable = false
//    )
//    private Long LastModifiedBy;


    public DrugRequestHandler() {
    }

    public DrugRequestHandler(Long drugId, String drugName,  Integer quantity, String rejectCause, Instant createAt,
                              Instant updateAt) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.quantity = quantity;
        this.rejectCause = rejectCause;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public DrugRequestHandler(Integer quantity, String drugName,
                              UnitInventoryRequestEnum requestStatus,
                              String timesDay,String timesServing, String does,
                              String roa, MedicineType type, String note, String rejectCause,
                              BaseUser signature, Instant createAt, Instant updateAt) {
        this.quantity = quantity;
        this.drugName = drugName;
        this.requestStatus = requestStatus;
        this.timesDay = timesDay;
        this.timesServing = timesServing;
        this.does = does;
        this.roa = roa;
        this.type = type;
        this.note = note;
        this.rejectCause = rejectCause;
        this.signature = signature;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    @PrePersist
    public void getCreate(){
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void getUpdate(){
        this.updateAt = Instant.now();
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public List<DrugExpanseTable> getExpanseTable() {
        return expanseTable;
    }


    public void setExpanseTable(List<DrugExpanseTable> expanseTable) {
        this.expanseTable = expanseTable;
    }





}
