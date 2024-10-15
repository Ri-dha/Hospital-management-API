package com.azu.hospital.Patients.Patient.request;

import com.azu.hospital.Patients.PatentEnum.IraqiProvinceEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PatientAddress {

    @NotNull(message = "Birth address Must not be null")
    @NotBlank(message = "Birth address Must not be blank")
    private String birthAddress;



    @NotNull(message = "Address Must not be null")
    @NotBlank(message = "Address Must not be blank")
    private String address;


    public PatientAddress() {
    }
    public PatientAddress(String birthAddress, String address) {
        this.birthAddress = birthAddress;
        this.address = address;
    }

}
