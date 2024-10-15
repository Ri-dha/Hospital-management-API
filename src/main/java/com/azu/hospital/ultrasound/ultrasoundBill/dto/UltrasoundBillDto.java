package com.azu.hospital.ultrasound.ultrasoundBill.dto;


import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UltrasoundBillDto {


    private Long id;

    private String note;

    private BigDecimal price;

    private UltrasoundTypeEnum type;

    public UltrasoundBillDto(Long id,
                             String note,
                             BigDecimal price,
                             UltrasoundTypeEnum type) {
        this.id = id;
        this.note = note;
        this.price = price;
        this.type = type;
    }

}
