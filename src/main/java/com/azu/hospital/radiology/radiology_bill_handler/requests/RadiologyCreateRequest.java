package com.azu.hospital.radiology.radiology_bill_handler.requests;


import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RadiologyCreateRequest {

    @NotNull(message = "Accountant note required")
    private String note;

    @NotNull(message = "Price required")
    private BigDecimal price;

    @NotNull(message = "Type required")
    @Enumerated
    private RadiologyTypeEnum type;

    public RadiologyCreateRequest() {
    }

    public RadiologyCreateRequest(String note, BigDecimal price, RadiologyTypeEnum type) {
        this.note = note;
        this.price = price;
        this.type = type;
    }
}
