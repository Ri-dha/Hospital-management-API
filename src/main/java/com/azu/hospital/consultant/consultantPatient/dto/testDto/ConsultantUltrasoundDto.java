package com.azu.hospital.consultant.consultantPatient.dto.testDto;

import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ConsultantUltrasoundDto {


    private Long id;

    private String note;

    private UltrasoundOrderState state;

    private Long patientId;

    private String patientName;

    private Instant date;

    public ConsultantUltrasoundDto(
            Long id,
            String note,
            UltrasoundOrderState state,
            Long patientId,
            String patientName,
            Instant date
    ) {
        this.id = id;
        this.note = note;
        this.state = state;
        this.patientId = patientId;
        this.patientName = patientName;
        this.date = date;
    }

    public ConsultantUltrasoundDto() {
    }
}
