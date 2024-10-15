package com.azu.hospital.operation.surgical_list.dto;


import java.math.BigDecimal;

public record MainSurgicalListDto(
        Long id,
        String name,
        BigDecimal price,
        String note
) {
}
