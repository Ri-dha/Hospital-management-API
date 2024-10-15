package com.azu.hospital.ecg.ecgbill.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EcgBillDto {

    private Long id;

    private String note;

    private BigDecimal price;


    public EcgBillDto(Long id,
                      String note,
                      BigDecimal price) {
        this.id = id;
        this.note = note;
        this.price = price;
    }


}
