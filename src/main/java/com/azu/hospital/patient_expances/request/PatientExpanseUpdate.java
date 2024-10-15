package com.azu.hospital.patient_expances.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientExpanseUpdate {
    private Long id;
    private String itemName;
    private Integer itemCount;
    private String note;

}
