package com.azu.hospital.ultrasound.internal.request;

import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInternalUltrasoundTestRequest {

    @NotNull(message = "Patient field required")
    private Long patientId;

    @NotNull(message = "Note field required")
    private String note;

    @NotNull(message = "Ultrasound type required")
    @Enumerated
    private UltrasoundTypeEnum type;

    public CreateInternalUltrasoundTestRequest() {
    }

    public CreateInternalUltrasoundTestRequest(Long patientId, String note, UltrasoundTypeEnum type) {
        this.patientId = patientId;
        this.note = note;
        this.type = type;
    }
}
