package com.azu.hospital.lab_collection.internal.int_tests_request.request;

import com.azu.hospital.utils.enums.lab.LabSpots;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class InternalTestRequestDataRequest{


        @NotNull
        private String testName;
        @NotNull
        @Enumerated
        private LabSpots spots;
        @NotNull
        private String note;

        public InternalTestRequestDataRequest(String testName,LabSpots spots, String note) {
                this.testName=testName;
                this.spots = spots;
                this.note = note;
        }

        public InternalTestRequestDataRequest() {
        }
}


