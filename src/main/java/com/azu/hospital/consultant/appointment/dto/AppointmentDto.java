package com.azu.hospital.consultant.appointment.dto;

import com.azu.hospital.utils.enums.appointment.AppointmentNewOrReview;
import com.azu.hospital.utils.enums.appointment.AppointmentsState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AppointmentDto {

    private Long id;

    private Integer number;

    private Instant date;

    private AppointmentsState state;

    private AppointmentNewOrReview patientState;

    private Long patientId;

    private Long doctorId;

    public AppointmentDto() {
    }

    public AppointmentDto(
            Long id,
            Integer number,
            Instant date,
            AppointmentsState state,
            AppointmentNewOrReview patientState,
            Long patientId,
            Long doctorId
    ) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.state = state;
        this.patientState = patientState;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }


}
