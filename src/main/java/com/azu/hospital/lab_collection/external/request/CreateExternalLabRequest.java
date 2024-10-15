package com.azu.hospital.lab_collection.external.request;

import com.azu.hospital.lab_collection.external.ext_tests_request.request.TestRequestDataDataRequest;
import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class CreateExternalLabRequest {
        @NotNull
        @NotBlank
        private String patientName;


        private String doctorName;

        @NotNull
        @Enumerated
        private Gender gender;

        @NotNull
        private String age;


        private String dx;


        private String allergy;

        private String note;

        private BigDecimal height;

        private BigDecimal weight;
        private List<TestRequestDataDataRequest> test;

        public CreateExternalLabRequest() {
        }

        public CreateExternalLabRequest(String patientName, String doctorName, Gender gender, String age, String dx, String allergy, String note, BigDecimal height, BigDecimal weight, List<TestRequestDataDataRequest> test) {
                this.patientName = patientName;
                this.doctorName = doctorName;
                this.gender = gender;
                this.age = age;
                this.dx = dx;
                this.allergy = allergy;
                this.note = note;
                this.height = height;
                this.weight = weight;
                this.test = test;
        }
}
