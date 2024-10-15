package com.azu.hospital.ultrasound.ultrasoundBill.requests;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UltrasoundUpdateRequest {

    private String note;

    private BigDecimal price;


    public UltrasoundUpdateRequest() {
    }
}
