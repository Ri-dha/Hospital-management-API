package com.azu.hospital.lab_collection.external.dto;

import com.azu.hospital.lab_collection.external.ext_tests_request.dto.ExtTestRequestDto;
import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dto.IntTestRequestDto;
import com.azu.hospital.utils.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExternalLabWithResultDto {
    private Long id;

    private String patientName;

    private String doctorName;

    private Gender gender;

    private String age;

    private String admissionDate;

    private List<ExtTestRequestDto> tests;

    private Long createdBy;

    private Long LastModifiedBy;


    public ExternalLabWithResultDto() {
    }

    public ExternalLabWithResultDto(Long id, String patientName, String doctorName,
                                    Gender gender, String age,
                                    String admissionDate, List<ExtTestRequestDto> tests,
                                    Long createdBy, Long lastModifiedBy
    ) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.gender = gender;
        this.age = age;
        this.admissionDate = admissionDate;
        this.tests = tests;
        this.createdBy = createdBy;
        this.LastModifiedBy = lastModifiedBy;
    }
}
