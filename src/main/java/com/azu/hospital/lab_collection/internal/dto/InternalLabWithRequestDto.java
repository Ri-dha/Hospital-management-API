package com.azu.hospital.lab_collection.internal.dto;

import com.azu.hospital.lab_collection.internal.int_tests_request.dto.IntTestRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InternalLabWithRequestDto {

    private Long labId;

    private Long patientId;

    private String doctorName;

    private String patientName;

    private String pateintGender;

    private String patientAge;

    private String bedNumber;

    private String wardName;

    private String admissionDate;

    private List<IntTestRequestDto> tests;

    private Long createdBy;
    private Long LastModifiedBy;


    public InternalLabWithRequestDto() {

    }

    public InternalLabWithRequestDto(Long labId, Long patientId,
                                     String doctorName, String patientName,
                                     String pateintGender, String patientAge,
                                     String bedNumber, String wardName,
                                     String admissionDate, List<IntTestRequestDto> tests,
                                     Long createdBy, Long lastModifiedBy
    ) {
        this.labId = labId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.pateintGender = pateintGender;
        this.patientAge = patientAge;
        this.bedNumber = bedNumber;
        this.wardName = wardName;
        this.admissionDate = admissionDate;
        this.tests = tests;
        this.createdBy = createdBy;
        LastModifiedBy = lastModifiedBy;
    }
}
