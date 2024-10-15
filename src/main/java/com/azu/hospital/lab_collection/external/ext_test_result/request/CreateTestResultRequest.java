package com.azu.hospital.lab_collection.external.ext_test_result.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

public record CreateTestResultRequest (
        @NotNull

        @NotNull
        @Valid
        List<TestResultDataRequest> results
){}



