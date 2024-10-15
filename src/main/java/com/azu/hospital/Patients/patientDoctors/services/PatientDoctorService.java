package com.azu.hospital.Patients.patientDoctors.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.patientDoctors.dao.PatientDoctorDao;
import com.azu.hospital.Patients.patientDoctors.dto.PatientDoctorDto;
import com.azu.hospital.Patients.patientDoctors.dto.PatientDoctorDtoMapper;
import com.azu.hospital.Patients.patientDoctors.entity.PatientDoctor;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.PatientDoctorState;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientDoctorService {


    private final PatientDoctorDao patientDoctorDao;

    private final PatientDao patientDao;

    private final DoctorDao doctorDao;

    private final PatientDoctorDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public PatientDoctorService(
            @Qualifier("patientDoctorJpa") PatientDoctorDao patientDoctorDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            PatientDoctorDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.patientDoctorDao = patientDoctorDao;
        this.patientDao = patientDao;
        this.doctorDao = doctorDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }



    @Transactional
    public PatientDoctorDto addPatientToDoctor(Long patientId, Long doctorId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Doctor doctor = doctorDao.findDoctorById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (patientDoctorDao.existsByPatientIdAndDoctorId(patientId, doctorId))
            throw new DuplicateResourceException(
                    messages.getString("alreadyExists")
            );


        PatientDoctor patientDoctor = new PatientDoctor(PatientDoctorState.ConsultantDoctor);

        patientDoctor.setPatient(patient);

        patientDoctor.setDoctor(doctor);

        patientDoctor = patientDoctorDao.newPatientDoctor(patientDoctor);

        patient.getDoctor().add(patientDoctor);

        return mapper.toDto(patientDoctor);

    }



    @Transactional
    public void transferringDoctorToPatient(Long patientId, Long doctorId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Doctor doctor = doctorDao.findDoctorById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        PatientDoctor mainPatientDoctor = patientDoctorDao.getMainDoctor(patientId);

        if (mainPatientDoctor.getDoctor().getId().equals(doctorId)) {
            throw new BadRequestException(
                    messages.getString("alreadyExists")
            );
        }

        Optional<PatientDoctor> patientDoctor = patientDoctorDao.findByPatientIdAndDoctorId(patientId,doctorId);

        if (patientDoctor.isPresent()){

            List<PatientDoctor> patientDoctors = new ArrayList<>();
            mainPatientDoctor.setState(PatientDoctorState.ConsultantDoctor);
            patientDoctor.get().setState(PatientDoctorState.Main);

            patientDoctors.add(patientDoctor.orElseThrow());
            patientDoctors.add(mainPatientDoctor);

            patientDoctorDao.updateAllPatientDoctor(patientDoctors);

        }else{

            mainPatientDoctor.setState(PatientDoctorState.ConsultantDoctor);

            patientDoctorDao.updatePatientDoctor(mainPatientDoctor);

            PatientDoctor newPatientDoctor = new PatientDoctor(PatientDoctorState.Main);

            newPatientDoctor.setPatient(patient);

            newPatientDoctor.setDoctor(doctor);

            newPatientDoctor = patientDoctorDao.newPatientDoctor(newPatientDoctor);

            patient.getDoctor().add(newPatientDoctor);


        }

    }

    public List<PatientDoctorDto> getPatientDoctors(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );


       return patientDoctorDao
               .getAllPatientId(patientId)
               .stream()
               .map(mapper::toDto)
               .collect(Collectors.toList());

    }






}
