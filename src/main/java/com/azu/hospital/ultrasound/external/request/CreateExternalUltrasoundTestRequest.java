package com.azu.hospital.ultrasound.external.request;

import com.azu.hospital.ultrasound.external.entity.UltrasoundPatientData;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateExternalUltrasoundTestRequest {


    private String note;

    @NotNull(message = "Ultrasound type must not be null")
    private UltrasoundTypeEnum ultrasoundType;

    @NotNull(message = "Patient data must not be null")
    @Valid
    private UltrasoundPatientData patientData;


    public CreateExternalUltrasoundTestRequest() {
    }
}
