package com.azu.hospital.radiology.external.entity;

import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Embeddable
@Getter
@Setter
public class RadiologyPatientData {

    @NotNull
    @NotBlank
    private String patientName;

    private String doctorName;

    @NotNull
    @Enumerated
    private Gender gender;


    @NotNull
    private String age;


    @Column(columnDefinition = "TEXT")
    private String dx;

    @Column(columnDefinition = "TEXT")
    private String allergy;

    public RadiologyPatientData(
            String patientName,
            String doctorName,
            Gender gender,
            String age,
            String dx,
            String allergy
    ) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.gender = gender;
        this.age = age;
        this.dx = dx;
        this.allergy = allergy;
    }

    public RadiologyPatientData() {
    }
}
