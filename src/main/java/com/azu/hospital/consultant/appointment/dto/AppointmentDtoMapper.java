package com.azu.hospital.consultant.appointment.dto;

import com.azu.hospital.consultant.appointment.entity.Appointment;
import org.springframework.stereotype.Service;

@Service
public class AppointmentDtoMapper {
    public AppointmentDto toDto(Appointment appointment){
        return new AppointmentDto(
                appointment.getId(),
                appointment.getNumber(),
                appointment.getDate(),
                appointment.getState(),
                appointment.getPatientState(),
                appointment.getPatient() == null ?null:appointment.getPatient().getId(),
                appointment.getDoctor()== null ? null :appointment.getDoctor().getId()

        );
    }
}
