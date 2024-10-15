package com.azu.hospital.ultrasound.internal.dto;

import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternalUltrasoundBillDto {

    private Long id;

    private Integer count;

    private UltrasoundTypeEnum type;

    private BigDecimal price;

    private BigDecimal totalPrice;
}
