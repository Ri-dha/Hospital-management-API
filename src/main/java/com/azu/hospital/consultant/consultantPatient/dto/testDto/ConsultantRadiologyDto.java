package com.azu.hospital.consultant.consultantPatient.dto.testDto;


import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
public class ConsultantRadiologyDto {


    private Long id;

    private String note;

    private RadiologyTypeEnum type;

    private RadiologyOrderState state;

    private Long patientId;

    private String patientName;

    private Instant date;

    public ConsultantRadiologyDto(
            Long id,
            String note,
            RadiologyTypeEnum type,
            RadiologyOrderState state,
            Long patientId,
            String patientName,
            Instant date
    ) {
        this.id = id;
        this.note = note;
        this.type = type;
        this.state = state;
        this.patientId = patientId;
        this.patientName = patientName;
        this.date = date;
    }

    public ConsultantRadiologyDto() {
    }
}
