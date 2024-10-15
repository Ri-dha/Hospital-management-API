package com.azu.hospital.consultant.appointment.dao;

import com.azu.hospital.consultant.appointment.dto.AppointmentStatsDTO;
import com.azu.hospital.consultant.appointment.entity.Appointment;
import com.azu.hospital.utils.enums.appointment.AppointmentsState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository("appointmentRepo")
public class AppointmentDataJps implements AppointmentDao {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentDataJps(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public Optional<Appointment> getByID(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public Boolean existsByDateAndState(Instant date, AppointmentsState state) {
        return appointmentRepository.existsByDateAndState(date,state);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findAllByDate(Instant date) {
        return appointmentRepository.findAllByDate(date);
    }

    @Override
    public Appointment createNewAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Long countByDate(Instant date) {
        return appointmentRepository.countByDate(date);
    }

    @Override
    public Boolean existsByDateAndNumberAndDoctorId(Instant date, Integer number, Long id) {
        return appointmentRepository.existsByDateAndNumberAndDoctorId(date, number , id);
    }

    @Override
    public List<AppointmentStatsDTO> findByFullDate(Long doctorId , Integer year , Integer month) {
        return appointmentRepository.findByDateYearAndMonth(doctorId ,year , month);
    }

    @Override
    public Page<Appointment> getAllAppointmentByDoctorIdAndDate(Instant date , Long doctorId , Pageable pageable) {

        return appointmentRepository.getAllByDateAndDoctorIdOrderByNumber(date , doctorId , pageable);
    }
}
