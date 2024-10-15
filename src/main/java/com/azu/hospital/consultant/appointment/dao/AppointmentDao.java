package com.azu.hospital.consultant.appointment.dao;

import com.azu.hospital.consultant.appointment.dto.AppointmentStatsDTO;
import com.azu.hospital.consultant.appointment.entity.Appointment;
import com.azu.hospital.utils.enums.appointment.AppointmentsState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface AppointmentDao {

    Optional<Appointment> getByID(Long appointmentId);

    Boolean existsByDateAndState(Instant date, AppointmentsState state);
    void updateAppointment(Appointment appointment);
    List<Appointment> findAllByDate(Instant date);
    Appointment createNewAppointment(Appointment appointment);
    Long countByDate(Instant date);

    Boolean existsByDateAndNumberAndDoctorId(Instant date , Integer number, Long id);

    List<AppointmentStatsDTO> findByFullDate(Long doctorId , Integer year , Integer month);
    Page<Appointment> getAllAppointmentByDoctorIdAndDate(Instant date , Long doctorId , Pageable pageable);

}
