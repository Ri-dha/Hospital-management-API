package com.azu.hospital.lab_collection.blood_bank.Bottle.request;

import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateNewBottleRequest {

    @NotNull
    @Enumerated(EnumType.STRING)
    private BottleTypeEnum bottleType;


    @Enumerated(EnumType.STRING)
    private BloodGroupEnum bloodGroup;



    @NotNull
    @NotBlank
    private String bottleNumber;


    @NotNull
    @NotBlank
    private String donatorName;

    @NotNull
    @NotBlank
    private String donatorPhoneNumber;

    @NotNull
    @NotBlank
    @DatePattern
    private String fillDate;

    @NotNull
    @NotBlank
    @DatePattern
    private String expiredDate;


    public CreateNewBottleRequest() {
    }
}
