package com.azu.hospital.accounting.all_item_expanse.opreation.entity;

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
@Table(name = "patient_operation_expanse_result_table")
@Getter
@Setter
public class PatientOperationExpanseResultTable implements BaseExpansePatient {
    @Id
    @SequenceGenerator(
            name = "patient_operation_expanse_result_table_sequence",
            sequenceName = "patient_operation_expanse_result_table_sequence",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_operation_expanse_result_table_sequence"
    )
    private Long id;

    private Long operationId;

    private String operationName;

    private Integer operationCount;

    private BigDecimal operationPrice;

    private BigDecimal totalOperationPrice;

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


    public PatientOperationExpanseResultTable() {
    }

    public PatientOperationExpanseResultTable(String operationName, Integer operationCount, BigDecimal operationPrice, BigDecimal totalOperationPrice) {
        this.operationName = operationName;
        this.operationCount = operationCount;
        this.operationPrice = operationPrice;
        this.totalOperationPrice = totalOperationPrice;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getItemId() {
        return operationId;
    }

    @Override
    public String getItemName() {
        return operationName;
    }

    @Override
    public Integer getItemCount() {
        return operationCount;
    }

    @Override
    public BigDecimal getItemPrice() {
        return operationPrice;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return totalOperationPrice;
    }
}
