package com.azu.hospital.accounting.all_item_expanse.Labs.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.BaseExpansePatient;
import com.azu.hospital.utils.enums.StockBillState;
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
@Table(name = "patient_lab_expanse_result_table")
@Getter
@Setter
public class PatientLabExpanseResultTable implements BaseExpansePatient {
    @Id
    @SequenceGenerator(
            name = "patient_lab_expanse_result_table_sequence",
            sequenceName = "patient_lab_expanse_result_table_sequence",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_lab_expanse_result_table_sequence"
    )
    private Long id;

    private Long labTestId;

    private String labTestName;
    private Integer labTestCount;
    private BigDecimal labTestPrice;
    private BigDecimal totalLabTestPrice;

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


    public PatientLabExpanseResultTable() {
    }

    public PatientLabExpanseResultTable(String labTestName, Integer labTestCount, BigDecimal labTestPrice, BigDecimal totalLabTestPrice) {
        this.labTestName = labTestName;
        this.labTestCount = labTestCount;
        this.labTestPrice = labTestPrice;
        this.totalLabTestPrice = totalLabTestPrice;
    }
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getItemId() {
        return labTestId;
    }

    @Override
    public String getItemName() {
        return labTestName;
    }

    @Override
    public Integer getItemCount() {
        return labTestCount;
    }

    @Override
    public BigDecimal getItemPrice() {
        return labTestPrice;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return totalLabTestPrice;
    }
}
