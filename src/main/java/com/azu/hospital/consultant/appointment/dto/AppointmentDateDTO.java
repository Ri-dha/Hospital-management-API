package com.azu.hospital.consultant.appointment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDateDTO {

    private String dayName;

    private int dayNum;

    private Object count;

    public AppointmentDateDTO() {
    }

    public AppointmentDateDTO(String dayName, int dayNum, Object count) {
        this.dayName = dayName;
        this.dayNum = dayNum;
        this.count = count;
    }
}