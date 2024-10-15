package com.azu.hospital.lab_collection.external.ext_tests_request.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class CreateTestsForExternalLabTest {
        @NotNull
        private Long extTestId;

        @NotNull
        @Valid
        private List<TestRequestDataDataRequest> test;

        public CreateTestsForExternalLabTest(Long extTestId, List<TestRequestDataDataRequest> test) {
                this.extTestId = extTestId;
                this.test = test;
        }

        public CreateTestsForExternalLabTest() {
        }
}
