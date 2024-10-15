package com.azu.hospital.consultant.consultantPatient.dto.testDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
public class ConsultantLabDto {

    private Long id;

    private Instant createdAt;

    private String state;

    private String note;

    private Long nurseId;

    private String nurseName;

    private Long patientId;

    private String patientName;

    public ConsultantLabDto(
            Long id,
            Instant createdAt,
            String state,
            String note,
            Long nurseId,
            String nurseName,
            Long patientId,
            String patientName

    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.state = state;
        this.note = note;
        this.nurseId = nurseId;
        this.nurseName = nurseName;
        this.patientId = patientId;
        this.patientName = patientName;

    }
    public ConsultantLabDto() {
    }
}
