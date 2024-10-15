package com.azu.hospital.lab_collection.internal.int_test_result.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public record InternalTestResultDataRequest
        (@NotNull
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


