package com.azu.hospital.consultant.appointment.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.consultant.appointment.dao.AppointmentDao;
import com.azu.hospital.consultant.appointment.entity.Appointment;
import com.azu.hospital.consultant.appointment.request.CreateNewAppointmentRequest;
import com.azu.hospital.consultant.appointment.dto.*;
import com.azu.hospital.consultant.consultantPatient.dao.ConsultantPatientDao;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.appointment.AppointmentNewOrReview;
import com.azu.hospital.utils.enums.appointment.AppointmentsState;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class AppointmentService {
    private static final String APPOINTMENT_TIME_SUFFIX = "T10:30:00Z";
    private final ConsultantPatientDao consultantPatientDao;
    private final DoctorDao doctorDao;
    private final AppointmentDao appointmentDao;
    private final AppointmentDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public AppointmentService(
            @Qualifier("consultantPatientJpa") ConsultantPatientDao consultantPatientDao,
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            @Qualifier("appointmentRepo") AppointmentDao appointmentDao,
            AppointmentDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.consultantPatientDao = consultantPatientDao;
        this.doctorDao = doctorDao;
        this.appointmentDao = appointmentDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public AppointmentDto createNewAppointment(CreateNewAppointmentRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Doctor doctor = doctorDao.findDoctorById(1L).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        Instant date = Instant.parse(request.getDate() + APPOINTMENT_TIME_SUFFIX);


        if (appointmentDao.countByDate(date) >= doctor.getAppointmentNumber()) {
            throw new BadRequestException("Appointments have reached their maximum");
        } else if (appointmentDao.existsByDateAndNumberAndDoctorId(date, request.getNumber(), doctor.getId())) {
            throw new BadRequestException(
                    messages.getString("resourceFound")
            );
        }

        ConsultantPatient patient = consultantPatientDao.findByPatientId(request.getPatientId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        Appointment appointment = new Appointment(
                request.getNumber(),
                date,
                AppointmentsState.Waitting,
                request.getIsNew() ? AppointmentNewOrReview.New : AppointmentNewOrReview.Review
        );

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointment = appointmentDao.createNewAppointment(appointment);


        return mapper.toDto(appointment);

    }



    public Page<AppointmentDto> getAppointmentByDoctorIdAndDate(Long doctorId, String date, Pageable pageable) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Doctor doctor = doctorDao.findDoctorById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")

                )
        );

        dateValidate(date);

        Instant newDate = Instant.parse(date + APPOINTMENT_TIME_SUFFIX);
        return appointmentDao.getAllAppointmentByDoctorIdAndDate(newDate, doctorId, pageable).map(mapper::toDto);

    }


    public List<AppointmentDateDTO> getAppointmentDates(Long doctorId, String date) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Doctor doctor = doctorDao.findDoctorById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        dateValidate(date);

        LocalDate newDate = LocalDate.parse(date);

        List<AppointmentStatsDTO> appointments = appointmentDao.findByFullDate(doctorId, newDate.getYear(), newDate.getMonthValue());

        List<AppointmentDateDTO> data = new ArrayList<>();

        for (AppointmentStatsDTO value : appointments) {
            LocalDate localDate = LocalDate.parse(value.getApptDay().toString());

            AppointmentDateDTO appointmentDateDTO = new AppointmentDateDTO(
                    localDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US),
                    localDate.getDayOfMonth(),
                    value.getApptCount()
            );

            data.add(appointmentDateDTO);
        }

        return data;

    }

    public List<ValidAppointmentDto> getValidAppointment(Long doctorId, String date) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Doctor doctor = doctorDao.findDoctorById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        dateValidate(date);

        Instant newDate = Instant.parse(date + APPOINTMENT_TIME_SUFFIX);

        List<Appointment> appointments = appointmentDao.findAllByDate(newDate);

        List<Integer> number = appointments.stream()
                .map(Appointment::getNumber)
                .toList();

        List<ValidAppointmentDto> data = new ArrayList<>();

        for (int i = 1; i <= doctor.getAppointmentNumber(); i++) {
            ValidAppointmentDto validAppointmentDto = new ValidAppointmentDto();



            if (number.contains(i)) {
                validAppointmentDto.setNumber(i);
                validAppointmentDto.setIsValid(false);
            } else {
                validAppointmentDto.setNumber(i);
                validAppointmentDto.setIsValid(true);            }
            data.add(validAppointmentDto);

        }

        return data;

    }


    @Transactional
    public AppointmentDto changeState(Long appointmentId, Boolean isCancel) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Appointment appointments = appointmentDao.getByID(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (isCancel) {
            if (appointments.getState() != AppointmentsState.Waitting) {
                throw new BadRequestException(
                        messages.getString("cannotAccept")

                );
            }
            appointments.setState(AppointmentsState.CANCEL);
            appointmentDao.updateAppointment(appointments);
        } else {
            switch (appointments.getState()) {
                case Waitting -> {
                    if (appointmentDao.existsByDateAndState(appointments.getDate(), AppointmentsState.With_The_Doctor)) {
                        throw new BadRequestException(
                                messages.getString("cannotAccept")
                        );
                    }
                    appointments.setState(AppointmentsState.With_The_Doctor);
                    appointmentDao.updateAppointment(appointments);
                }

                case With_The_Doctor -> {
                    if (!appointmentDao.existsByDateAndState(appointments.getDate(), AppointmentsState.With_The_Doctor)) {
                        throw new BadRequestException(
                                messages.getString("resourceNotFound")
                        );

                    }
                    appointments.setState(AppointmentsState.Done);
                    appointmentDao.updateAppointment(appointments);
                }
                case Done -> {
                    throw new BadRequestException("Can't Change State Because This Patient Complete Appointment");
                }
                case CANCEL -> {
                    throw new BadRequestException(
                            messages.getString("alreadyExist")

                    );

                }

            }
        }

        return mapper.toDto(appointments);

    }

    private void dateValidate(String date) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        if (date == null) {
            throw new ResourceNotFoundException(
                    messages.getString("fieldRequired")
            );
        } else {
            String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
            Pattern pattern = Pattern.compile(DATE_PATTERN);
            Matcher matcher = pattern.matcher(date);

            if (!matcher.matches()) {
                throw new ResourceNotFoundException(
                        messages.getString("invalidDate")
                );
            }

        }
    }
}
