package com.azu.hospital.ultrasound.ultrasoundBill.requests;

import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class UltrasoundBillCreateRequest {


    private String accountantNote;



    private BigDecimal price;


    @NotNull(message = "Type required")
    @Enumerated
    private UltrasoundTypeEnum type;


    public UltrasoundBillCreateRequest() {
    }

    public UltrasoundBillCreateRequest(String accountantNote, BigDecimal price, UltrasoundTypeEnum type) {
        this.accountantNote = accountantNote;
        this.price = price;
        this.type = type;
    }

}
