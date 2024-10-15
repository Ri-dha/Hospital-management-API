package com.azu.hospital.radiology.internal.request;

import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateInternalRadiologyTestRequest {

    @NotNull(message = "Patient field required")
    private Long patientId;

    @NotNull(message = "Note field required")
    private String note;

    @NotNull(message = "Radiology type required")
    @Enumerated
    private RadiologyTypeEnum type;

    public CreateInternalRadiologyTestRequest() {
    }

    public CreateInternalRadiologyTestRequest(Long patientId, String note, RadiologyTypeEnum type) {
        this.patientId = patientId;
        this.note = note;
        this.type = type;
    }
}
