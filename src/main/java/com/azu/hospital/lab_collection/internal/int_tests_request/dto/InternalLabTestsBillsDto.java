package com.azu.hospital.lab_collection.internal.int_tests_request.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternalLabTestsBillsDto {

    private Long testId;

    private Integer count;

    private String testName;

    private BigDecimal price;

    private BigDecimal totalPrice;



}
