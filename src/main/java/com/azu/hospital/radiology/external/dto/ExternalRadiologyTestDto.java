package com.azu.hospital.radiology.external.dto;

import com.azu.hospital.radiology.external.entity.RadiologyPatientData;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ExternalRadiologyTestDto {

    private Long id;

    private String note;

    private RadiologyTypeEnum type;

    private RadiologyOrderState state;


    private RadiologyPatientData patientData;

    private Instant date;

    private Long createdBy;
    private Long LastModifiedBy;


    public ExternalRadiologyTestDto(
            Long id,
            String note,
            RadiologyTypeEnum type,
            RadiologyOrderState state,
            RadiologyPatientData patientData,
            Instant date,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.note = note;
        this.type = type;
        this.state = state;
        this.patientData = patientData;
        this.date = date;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }

    public ExternalRadiologyTestDto() {
    }
}
