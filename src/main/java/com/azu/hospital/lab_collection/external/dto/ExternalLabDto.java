package com.azu.hospital.lab_collection.external.dto;

import com.azu.hospital.utils.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExternalLabDto {

    private Long id;

    private String patientName;

    private String doctorName;

    private Gender gender;

    private String age;

    private String admissionDate;

    private String state;

    private String dx;

    private String allergy;

    private String note;

    private BigDecimal height;

    private BigDecimal weight;

    private Long createdBy;
    private Long LastModifiedBy;


    public ExternalLabDto(
            Long id,
            String patientName,
            String doctorName,
            Gender gender,
            String age,
            String admissionDate,
            String state,
            String dx,
            String allergy,
            String note,
            BigDecimal height,
            BigDecimal weight,
            Long createdBy,
            Long lastModifiedBy
    ) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.gender = gender;
        this.age = age;
        this.admissionDate = admissionDate;
        this.state = state;
        this.dx = dx;
        this.allergy = allergy;
        this.note = note;
        this.height = height;
        this.weight = weight;
        this.createdBy = createdBy;
        this.LastModifiedBy = lastModifiedBy;
    }
}
