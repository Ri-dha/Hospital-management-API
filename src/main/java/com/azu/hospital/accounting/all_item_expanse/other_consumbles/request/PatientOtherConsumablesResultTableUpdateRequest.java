package com.azu.hospital.accounting.all_item_expanse.other_consumbles.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PatientOtherConsumablesResultTableUpdateRequest {


    private Long id;
    private BigDecimal price;


    public PatientOtherConsumablesResultTableUpdateRequest(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }
}
