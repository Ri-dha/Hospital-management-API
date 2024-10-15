package com.azu.hospital.accounting.all_item_expanse.opreation;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PatientOperationExpanseResultTableUpdateRequest {


    private Long id;
    private BigDecimal price;


    public PatientOperationExpanseResultTableUpdateRequest(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }
}
