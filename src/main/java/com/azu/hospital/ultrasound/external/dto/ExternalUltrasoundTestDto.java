package com.azu.hospital.ultrasound.external.dto;

import com.azu.hospital.ultrasound.external.entity.UltrasoundPatientData;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ExternalUltrasoundTestDto {

    private Long id;

    private String note;

    private UltrasoundTypeEnum type;


    private UltrasoundOrderState state;


    private UltrasoundPatientData patientData;

    private Instant date;

    private Long createdBy;
    private Long LastModifiedBy;


    public ExternalUltrasoundTestDto(
            Long id,
            String note,
            UltrasoundTypeEnum type,
            UltrasoundOrderState state,
            UltrasoundPatientData patientData,
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

    public ExternalUltrasoundTestDto() {
    }
}
