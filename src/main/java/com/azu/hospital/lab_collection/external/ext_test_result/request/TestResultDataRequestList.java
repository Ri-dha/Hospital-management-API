package com.azu.hospital.lab_collection.external.ext_test_result.request;

import java.util.List;

public record TestResultDataRequestList(

        Long testId,
        List<TestResultDataRequest> results,

        String note
) {
}
