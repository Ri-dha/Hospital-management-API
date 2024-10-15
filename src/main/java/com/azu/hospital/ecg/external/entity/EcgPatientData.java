package com.azu.hospital.ecg.external.entity;

import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Embeddable
@Data
public class EcgPatientData {

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

    private String mobile;

    public EcgPatientData(
            String patientName,
            String doctorName,
            Gender gender,
            Float weight,
            Float height,
            String age,
            String mobile
    ) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.mobile = mobile;
    }

    public EcgPatientData() {
    }
}
