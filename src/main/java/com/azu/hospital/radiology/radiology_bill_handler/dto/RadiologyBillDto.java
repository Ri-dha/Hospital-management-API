package com.azu.hospital.radiology.radiology_bill_handler.dto;

import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RadiologyBillDto {

    private Long id;

    private String note;

    private BigDecimal price;

    private RadiologyTypeEnum type;


    public RadiologyBillDto(Long id,
                            String note,
                            BigDecimal price,
                            RadiologyTypeEnum type) {
        this.id = id;
        this.note = note;
        this.price = price;
        this.type = type;
    }

}
