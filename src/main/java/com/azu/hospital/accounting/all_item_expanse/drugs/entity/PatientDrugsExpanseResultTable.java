package com.azu.hospital.accounting.all_item_expanse.drugs.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.BaseExpansePatient;
import com.azu.hospital.utils.enums.StockBillState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
        name = "patient_expanse_drug_result_tables_main"
)
@Getter
@Setter
public class PatientDrugsExpanseResultTable implements BaseExpansePatient {

    @Id
    @SequenceGenerator(
            name = "patient_expanse_drug_result_tables_main_seq_id",
            sequenceName = "patient_expanse_drug_result_tables_main_seq_id",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_expanse_drug_result_tables"
    )
    private Long id;

    private String drugName;
    private Integer drugCount;

    private final String drugsBill = "Drugs bill";

    private BigDecimal fullPrices;

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

    public PatientDrugsExpanseResultTable() {
    }

    public PatientDrugsExpanseResultTable(String drugName, Integer drugCount) {
        this.drugName = drugName;
        this.drugCount = drugCount;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getItemId() {
        return null;
    }

    @Override
    public String getItemName() {
        return getDrugName();
    }

    @Override
    public Integer getItemCount() {
        return getDrugCount();
    }

    @Override
    public BigDecimal getItemPrice() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return BigDecimal.ZERO;
    }
}
