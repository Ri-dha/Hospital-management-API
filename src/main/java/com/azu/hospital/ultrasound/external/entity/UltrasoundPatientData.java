package com.azu.hospital.ultrasound.external.entity;

import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Embeddable
@Getter
@Setter
public class UltrasoundPatientData {

    @NotNull
    @NotBlank
    private String patientName;

    private String doctorName;

    @NotNull
    @Enumerated
    private Gender gender;

    @NotNull
    @NumberFormat
    private Float weight;

    @NumberFormat
    private Float height;

    @NotNull
    private String age;

    public UltrasoundPatientData(
            String patientName,
            String doctorName,
            Gender gender,
            Float weight,
            Float height,
            String age
    ) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public UltrasoundPatientData() {
    }
}
