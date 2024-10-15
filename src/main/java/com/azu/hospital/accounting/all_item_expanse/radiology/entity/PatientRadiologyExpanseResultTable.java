package com.azu.hospital.accounting.all_item_expanse.radiology.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.BaseExpansePatient;
import com.azu.hospital.utils.enums.StockBillState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(
        name = "patient_radiology_expanse_result_tables_main"
)
@Getter
@Setter
public class PatientRadiologyExpanseResultTable implements BaseExpansePatient {
    @Id
    @SequenceGenerator(
            name = "patient_radiology_expanse_result_tables_main_seq_id",
            sequenceName = "patient_radiology_expanse_result_tables_main_seq_id",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_radiology_expanse_result_tables_main_seq_id"
    )
    private Long id;

    private Long radiologyId;

    private RadiologyTypeEnum radiologyName;

    private Integer radiologyCount;
    private BigDecimal radiologyPrice;
    private BigDecimal totalRadiologyPrice;

    private LocalDate dateOfPayment;


    private StockBillState state = StockBillState.UNPAID;


    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    private Boolean isArchived = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    public PatientRadiologyExpanseResultTable() {
    }


    public PatientRadiologyExpanseResultTable(RadiologyTypeEnum radiologyName, Integer radiologyCount, BigDecimal radiologyPrice, BigDecimal totalRadiologyPrice) {
        this.radiologyName = radiologyName;
        this.radiologyCount = radiologyCount;
        this.radiologyPrice = radiologyPrice;
        this.totalRadiologyPrice = totalRadiologyPrice;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getItemId() {
        return radiologyId;
    }

    @Override
    public String getItemName() {
        return radiologyName.getName();
    }

    @Override
    public Integer getItemCount() {
        return radiologyCount;
    }

    @Override
    public BigDecimal getItemPrice() {
        return radiologyPrice;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return totalRadiologyPrice;
    }
}
