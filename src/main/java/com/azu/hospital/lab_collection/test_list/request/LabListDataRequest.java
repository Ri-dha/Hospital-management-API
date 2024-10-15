package com.azu.hospital.lab_collection.test_list.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record LabListDataRequest(
        @NotNull
        @NotBlank
        String name,


        BigDecimal iqdPrice,

        BigDecimal usdPrice

) {

}
