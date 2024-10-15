package com.azu.hospital.Patients.Patient.request;


import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@Embeddable
public class PatientMedicalInfo {

    @Nullable
    private String apparentImpairments = "Nil";


    @Nullable
    private Integer timeOfEntries;

    @Nullable
    private String referredFrom;

    @Nullable
    private String transformations;


    public PatientMedicalInfo() {
    }

    public PatientMedicalInfo(
            @Nullable String apparentImpairments,
            @Nullable Integer timeOfEntries,
            @Nullable String referredFrom,
            @Nullable String transformations
    ) {
        this.apparentImpairments = apparentImpairments;
        this.timeOfEntries = timeOfEntries;
        this.referredFrom = referredFrom;
        this.transformations = transformations;

    }
}