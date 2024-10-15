package com.azu.hospital.Patients.Patient.request;

import com.azu.hospital.Validator.DateValidator.DatePattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.Trigger;

@Getter
@Setter
public class GetAllPatientRequest {

    private Integer page =0;

    private Integer size = 15;

    private String patientName = "";

    private Integer minAge = 0;

    private Integer maxAge = 200;

    private Long wardId;


    @DatePattern
    private String admissionDate = "";

    private String doctorName = "";


    public GetAllPatientRequest() {
    }
}