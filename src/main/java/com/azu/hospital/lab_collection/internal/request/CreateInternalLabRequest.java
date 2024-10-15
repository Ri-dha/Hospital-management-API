package com.azu.hospital.lab_collection.internal.request;

import com.azu.hospital.lab_collection.internal.int_tests_request.request.InternalTestRequestDataRequest;
import lombok.Data;

import java.util.List;
@Data
public class CreateInternalLabRequest{
       private String note;

       private List<InternalTestRequestDataRequest> testRequests;

    public CreateInternalLabRequest() {
    }

    public CreateInternalLabRequest(String note, List<InternalTestRequestDataRequest> testRequests) {
        this.note = note;
        this.testRequests = testRequests;
    }
}
