package com.azu.hospital.accounting.all_item_expanse.ecg.entity;

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
@Table(
        name = "patient_ecg_expanse_result_tables_main"
)
@Getter
@Setter
public class PatientEcgExpanseResultTable implements BaseExpansePatient {
    @Id
    @SequenceGenerator(
            name = "patient_ecg_expanse_result_tables_main_seq_id",
            sequenceName = "patient_ecg_expanse_result_tables_main_seq_id",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_ecg_expanse_result_tables_main_seq_id"
    )
    private Long id;

    private Long ecgId;

    private String ecgName="ECG";

    private Integer ecgCount;
    private BigDecimal ecgPrice;
    private BigDecimal totalEcgPrice;

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



    public PatientEcgExpanseResultTable() {
    }

    public PatientEcgExpanseResultTable(String ecgName, Integer ecgCount, BigDecimal ecgPrice, BigDecimal totalEcgPrice) {
        this.ecgName = ecgName;
        this.ecgCount = ecgCount;
        this.ecgPrice = ecgPrice;
        this.totalEcgPrice = totalEcgPrice;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getItemId() {
        return ecgId;
    }

    @Override
    public String getItemName() {
        return ecgName;
    }

    @Override
    public Integer getItemCount() {
        return ecgCount;
    }

    @Override
    public BigDecimal getItemPrice() {
        return ecgPrice;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return totalEcgPrice;
    }
}
