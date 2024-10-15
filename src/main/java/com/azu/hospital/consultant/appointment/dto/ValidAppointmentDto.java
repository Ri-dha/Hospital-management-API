package com.azu.hospital.consultant.appointment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidAppointmentDto {

    private int number;

    private Boolean isValid;


    public ValidAppointmentDto(int number, Boolean isValid) {
        this.number = number;
        this.isValid = isValid;
    }

    public ValidAppointmentDto() {
    }
}
