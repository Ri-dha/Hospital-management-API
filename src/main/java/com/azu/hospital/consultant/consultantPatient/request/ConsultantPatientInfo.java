package com.azu.hospital.consultant.consultantPatient.request;

import com.azu.hospital.Patients.PatentEnum.ReligionEnum;
import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.patient.SocialStateEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Embeddable
public class ConsultantPatientInfo {

    @NotNull
    @NotBlank
    private String fullName;



    private String motherName;


    @Enumerated
    private Gender gender;


    private BigDecimal wight;


    private BigDecimal height;


    private String mobile;


    @Enumerated
    private ReligionEnum religion;


    @Enumerated
    private SocialStateEnum socialState;


    public ConsultantPatientInfo() {
    }
}
