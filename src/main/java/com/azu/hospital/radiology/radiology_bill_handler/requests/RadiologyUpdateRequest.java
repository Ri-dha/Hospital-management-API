package com.azu.hospital.radiology.radiology_bill_handler.requests;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RadiologyUpdateRequest {

    private String note;

    private BigDecimal price;

    public RadiologyUpdateRequest() {
    }
}
