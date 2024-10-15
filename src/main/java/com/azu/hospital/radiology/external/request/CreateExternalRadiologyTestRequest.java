package com.azu.hospital.radiology.external.request;

import com.azu.hospital.radiology.external.entity.RadiologyPatientData;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateExternalRadiologyTestRequest {


    private String note;

    @NotNull
    @Enumerated
    private RadiologyTypeEnum type;

    @NotNull
    @Valid
    private RadiologyPatientData patientData;




    public CreateExternalRadiologyTestRequest() {
    }
}
