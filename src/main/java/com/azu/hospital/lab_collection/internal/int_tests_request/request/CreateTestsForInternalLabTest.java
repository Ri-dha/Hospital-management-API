package com.azu.hospital.lab_collection.internal.int_tests_request.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateTestsForInternalLabTest {
        @NotNull
        private Long intTestId;
        @NotNull
        private String testName;

        @NotNull
        @Valid
        @NotEmpty
       private List<InternalTestRequestDataRequest> test;
        @NotNull
        private String note;


}
