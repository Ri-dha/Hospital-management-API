package com.azu.hospital.accounting.all_item_expanse.soldBills.entity;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.BaseExpansePatient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sold_bill_expenses")
@Getter
@Setter
public class SoldBillExpenses implements BaseExpansePatient {

    @Id
    @SequenceGenerator(
            name = "sold_bill_expenses_sequence",
            sequenceName = "sold_bill_expenses_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sold_bill_expenses_sequence"
    )
    private Long id;








    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Long getItemId() {
        return null;
    }

    @Override
    public String getItemName() {
        return null;
    }

    @Override
    public Integer getItemCount() {
        return null;
    }

    @Override
    public BigDecimal getItemPrice() {
        return null;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }
}
