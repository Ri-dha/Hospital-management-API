package com.azu.hospital.lab_collection.test_list.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;


public record UpdateLabListRequest(

     String name,

     BigDecimal iqdPrice,

     BigDecimal usdPrice
) {


}
