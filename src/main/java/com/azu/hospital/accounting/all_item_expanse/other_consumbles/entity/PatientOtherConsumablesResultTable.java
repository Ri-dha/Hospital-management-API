package com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity;


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
@Getter
@Setter
@Table(name = "patient_other_consumables_result_tables_main")
public class PatientOtherConsumablesResultTable implements BaseExpansePatient {
    @Id
    @SequenceGenerator(
            name = "patient_other_consumables_result_tables_main_seq_id",
            sequenceName = "patient_other_consumables_result_tables_main_seq_id",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_other_consumables_result_tables"
    )
    private Long id;
    private Long itemId;

    private String itemName;

    private Integer itemCount;

    private BigDecimal itemPrice;

    private BigDecimal totalPrice;

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

    public PatientOtherConsumablesResultTable() {
    }

    public PatientOtherConsumablesResultTable(String itemName, Integer itemCount, BigDecimal itemPrice, BigDecimal totalPrice) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getItemId() {
        return itemId;
    }

    @Override
    public String getItemName() {
        return itemName;
    }


    @Override
    public Integer getItemCount() {
        return itemCount;
    }

    @Override

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
