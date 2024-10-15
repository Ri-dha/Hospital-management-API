package com.azu.hospital.ecg.ecgbill.request;

import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class EcgBillCreateRequest {

    @NotNull(message = "Accountant note required")
        private String accountantNote;


    @NotNull(message = "Price required")
    private BigDecimal price;



    public EcgBillCreateRequest() {
    }

    public EcgBillCreateRequest(String accountantNote, BigDecimal price, UltrasoundTypeEnum type) {
        this.accountantNote = accountantNote;
        this.price = price;
    }

}
