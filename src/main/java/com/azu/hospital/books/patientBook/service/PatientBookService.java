package com.azu.hospital.books.patientBook.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.books.patientBook.PatientBookType;
import com.azu.hospital.books.patientBook.dao.PatientBookDao;
import com.azu.hospital.books.patientBook.dto.PatientBookDto;
import com.azu.hospital.books.patientBook.dto.PatientBookDtoMapper;
import com.azu.hospital.books.patientBook.entity.PatientBook;
import com.azu.hospital.books.patientBook.request.PatientBookCreateRequest;
import com.azu.hospital.books.patientBook.request.PatientBookUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientBookService {


    private final PatientBookDao patientBookDao;
    private final BaseUserDao baseUserDao;

    private final PatientDao patientDao;

    private final PatientBookDtoMapper PatientBookDtoMapper;

    @Autowired
    public PatientBookService(@Qualifier("PatientBookDataRepository") PatientBookDao patientBookDao, BaseUserDao baseUserDao, PatientDao patientDao, com.azu.hospital.books.patientBook.dto.PatientBookDtoMapper patientBookDtoMapper) {
        this.patientBookDao = patientBookDao;
        this.baseUserDao = baseUserDao;
        this.patientDao = patientDao;
        PatientBookDtoMapper = patientBookDtoMapper;
    }


    public void createPatientBook(PatientBookCreateRequest request) {

        PatientBook patientBook = new PatientBook();
        patientBook.setPatientBookType(request.patientBookType());
        Patient patient = patientDao.getPatientById(request.patientId()).orElseThrow(
                () -> new IllegalStateException("Patient with id " + request.patientId() + " does not exist")
        );

        patientBook.setPatient(patient);

        if (request.doctorId() != null) {
            BaseUser doctor = baseUserDao.findById(request.doctorId()).orElseThrow(
                    () -> new IllegalStateException("Doctor with id " + request.doctorId() + " does not exist")
            );
            patientBook.setDoctor(doctor);
        }

        if (request.nurseId() != null) {
            BaseUser nurse = baseUserDao.findById(request.nurseId()).orElseThrow(
                    () -> new IllegalStateException("Nurse with id " + request.nurseId() + " does not exist")
            );
            patientBook.setNurse(nurse);
        }

        if (request.wardManagerId() != null) {
            BaseUser wardManager = baseUserDao.findById(request.wardManagerId()).orElseThrow(
                    () -> new IllegalStateException("Ward manager with id " + request.wardManagerId() + " does not exist")
            );
            patientBook.setWardManger(wardManager);
        }

        if (request.hospitalManagerId() != null) {
            BaseUser hospitalManager = baseUserDao.findById(request.hospitalManagerId()).orElseThrow(
                    () -> new IllegalStateException("Hospital manager with id " + request.hospitalManagerId() + " does not exist")
            );
            patientBook.setHospitalManager(hospitalManager);
        }


        patientBookDao.createPatientBookBody(patientBook);

    }

    public void updatePatientBook(PatientBookUpdateRequest request) {
        PatientBook book = patientBookDao.getPatientBookById(request.id()).orElseThrow(
                () -> new IllegalStateException("Patient book with id " + request.id() + " does not exist")
        );

        if (request.doctorId() != null) {
            BaseUser doctor = baseUserDao.findById(request.doctorId()).orElseThrow(
                    () -> new IllegalStateException("Doctor with id " + request.doctorId() + " does not exist")
            );
            book.setDoctor(doctor);
        }

        if (request.nurseId() != null) {
            BaseUser nurse = baseUserDao.findById(request.nurseId()).orElseThrow(
                    () -> new IllegalStateException("Nurse with id " + request.nurseId() + " does not exist")
            );
            book.setNurse(nurse);
        }

        if (request.wardManagerId() != null) {
            BaseUser wardManager = baseUserDao.findById(request.wardManagerId()).orElseThrow(
                    () -> new IllegalStateException("Ward manager with id " + request.wardManagerId() + " does not exist")
            );
            book.setWardManger(wardManager);
        }

        if (request.hospitalManagerId() != null) {
            BaseUser hospitalManager = baseUserDao.findById(request.hospitalManagerId()).orElseThrow(
                    () -> new IllegalStateException("Hospital manager with id " + request.hospitalManagerId() + " does not exist")
            );
            book.setHospitalManager(hospitalManager);
        }
        if (request.patientId() != null) {
            Patient patient = patientDao.getPatientById(request.patientId()).orElseThrow(
                    () -> new IllegalStateException("Patient with id " + request.patientId() + " does not exist")
            );
            book.setPatient(patient);
        }
        patientBookDao.updatePatientBook(book);

    }

    public void deletePatientBook(Long id) {
        patientBookDao.deletePatientBook(id);
    }


    public PatientBookDto getPatientBookById(Long id) {
        return patientBookDao
                .getPatientBookById(id)
                .map(PatientBookDtoMapper)
                .orElse(null);
    }


    public Page<PatientBookDto> getPatientBookByTypeAndByPatientId(Long patientId, PatientBookType type, Pageable pageable) {
        return patientBookDao
                .getAllPatientBookByTypeAndByPatientId(type, patientId, pageable)
                .map(PatientBookDtoMapper);
    }

    public Page<PatientBookDto> getAllPatientBook(Pageable pageable) {
        return patientBookDao
                .getAllPatientBook(pageable)
                .map(PatientBookDtoMapper);
    }

}
