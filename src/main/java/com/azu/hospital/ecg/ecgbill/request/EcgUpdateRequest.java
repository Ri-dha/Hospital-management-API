package com.azu.hospital.ecg.ecgbill.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EcgUpdateRequest {


    private String accountantNote;

    private BigDecimal price;


    public EcgUpdateRequest() {
    }
}
