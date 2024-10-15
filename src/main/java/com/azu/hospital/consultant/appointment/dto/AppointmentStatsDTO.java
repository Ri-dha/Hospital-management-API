package com.azu.hospital.consultant.appointment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AppointmentStatsDTO {

    private LocalDate apptDay;

    private Long apptCount;

    public AppointmentStatsDTO() {
    }

}