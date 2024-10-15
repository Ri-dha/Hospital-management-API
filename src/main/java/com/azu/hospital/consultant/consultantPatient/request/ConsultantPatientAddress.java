package com.azu.hospital.consultant.consultantPatient.request;

import com.azu.hospital.Patients.PatentEnum.ReligionEnum;
import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.patient.LiveEnum;
import com.azu.hospital.utils.enums.patient.SocialStateEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Embeddable
public class ConsultantPatientAddress {


    private String birthAddress;


    private String address;


    @Enumerated
    private LiveEnum live;


    public ConsultantPatientAddress() {
    }
}
