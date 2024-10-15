
package com.azu.hospital.Patients.Patient.request;

import com.azu.hospital.Patients.PatentEnum.CertificationEnum;
import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.patient.LiveEnum;
import com.azu.hospital.utils.enums.patient.SocialStateEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@Embeddable

public class PatientData {

    @NotNull(message = "Full name must not be null")
    @NotBlank(message = "Full name must not be null")
    @Column(unique = true)
    private String fullName;

    @NotNull(message = "Mother name must not be null")
    @NotBlank(message = "Mother name must not be blank")
    private String motherName;

    @NotNull(message = "Gender must not be null")
    @Enumerated
    private Gender gender;

    @NotNull(message = "Weight must not be null")
    @NumberFormat
    private Float weight;

    @NotNull(message = "Height must not be null")
    @NumberFormat
    private Float height;

    @NotNull(message = "Certification must not be null")
    @Enumerated
    private CertificationEnum certification;

    @NotNull(message = "Live must not be null")
    @Enumerated
    private LiveEnum liveEnum;

    @NotNull(message = "Social state must not be null")
    @Enumerated
    private SocialStateEnum socialState;

    private String operation;

    public PatientData() {
    }

    public PatientData(
            String fullName,
            String motherName,
            Gender gender,
            Float weight,
            Float height,
            CertificationEnum certification,
            LiveEnum liveEnum,
            SocialStateEnum socialState

    ) {
        this.fullName = fullName;
        this.motherName = motherName;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.certification = certification;
        this.liveEnum = liveEnum;
        this.socialState = socialState;
    }
}
