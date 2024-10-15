package com.azu.hospital.Patients.Patient.request;


import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PatientContact {

    @Nullable
    @Email(
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "Email Not Valid"
    )
    private String email;

    @NotNull(message = "Mobile must not be null")
    @NotBlank(message = "Mobile must not be blank")
    @Size(min = 10 , max = 10 , message = "Mobile must contains 10 number")
    private String mobile;

    @Nullable
    @Size(min = 10 , max = 10 , message = "Mobile must contains 10 number")
    private String relativeMobile;

    public PatientContact() {
    }

    public PatientContact(String email, String mobile, String relativeMobile) {
        this.email = email;
        this.mobile = mobile;
        this.relativeMobile = relativeMobile;
    }
}
