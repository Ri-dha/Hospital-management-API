package com.azu.hospital.accounting.all_item_expanse.ultrasound.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.BaseExpansePatient;
import com.azu.hospital.utils.enums.StockBillState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(
        name = "patient_ultrasound_expanse_result_tables_main")
@Getter
@Setter
public class PatientUltrasoundExpanseResultTable implements BaseExpansePatient {
    @Id
    @SequenceGenerator(
            name = "patient_ultrasound_expanse_result_tables_main_seq_id",
            sequenceName = "patient_ultrasound_expanse_result_tables_main_seq_id",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_ultrasound_expanse_result_tables_main_seq_id"
    )
    private Long id;

    private Long ultrasoundId;

    private UltrasoundTypeEnum ultrasoundName;

    private Integer ultrasoundCount;

    private BigDecimal ultrasoundPrice;

    private BigDecimal totalUltrasoundPrice;

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

    public PatientUltrasoundExpanseResultTable() {
    }

    public PatientUltrasoundExpanseResultTable(UltrasoundTypeEnum ultrasoundName, Integer ultrasoundCount, BigDecimal ultrasoundPrice, BigDecimal totalUltrasoundPrice) {
        this.ultrasoundName = ultrasoundName;
        this.ultrasoundCount = ultrasoundCount;
        this.ultrasoundPrice = ultrasoundPrice;
        this.totalUltrasoundPrice = totalUltrasoundPrice;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getItemId() {
        return ultrasoundId;
    }

    @Override
    public String getItemName() {
        return ultrasoundName.getName();
    }

    @Override
    public Integer getItemCount() {
        return ultrasoundCount;
    }

    @Override
    public BigDecimal getItemPrice() {
        return ultrasoundPrice;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return totalUltrasoundPrice;
    }
}
