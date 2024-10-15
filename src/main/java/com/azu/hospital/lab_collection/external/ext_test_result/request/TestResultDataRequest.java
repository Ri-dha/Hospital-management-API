package com.azu.hospital.lab_collection.external.ext_test_result.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TestResultDataRequest(


        @NotNull
        @NotBlank
        String resultName,

        @NotNull
        @NotBlank
        String testResult,

        @NotNull
        @NotBlank
        String normalValue


) {


}
