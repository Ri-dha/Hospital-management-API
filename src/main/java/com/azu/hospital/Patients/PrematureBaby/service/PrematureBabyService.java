package com.azu.hospital.Patients.PrematureBaby.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.PrematureBaby.dao.PrematureBabyDao;
import com.azu.hospital.Patients.PrematureBaby.dto.PrematureBabyDto;
import com.azu.hospital.Patients.PrematureBaby.dto.PrematureBabyDtoMapper;
import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import com.azu.hospital.Patients.PrematureBaby.requests.PrematureBabyCreateRequest;
import com.azu.hospital.Patients.PrematureBaby.requests.PrematureBabyUpdateRequest;
import com.azu.hospital.Patients.PrematureBaby.utli.PrematureBabyDate;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.bulding.ward.wardBed.dao.BedDao;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PrematureBabyService {

    private final PrematureBabyDao prematureBabyDao;

    private final PrematureBabyDtoMapper prematureBabyDtoMapper;

    private final PatientDao patientDao;
    private final DoctorDao doctorDao;
    private final BedDao bedDao;

    private final WardDao wardDao;

    @Autowired
    public PrematureBabyService(@Qualifier("PrematureBabyDataJpa") PrematureBabyDao prematureBabyDao,
                                @Qualifier("prematureBabyDtoMapper") PrematureBabyDtoMapper prematureBabyDtoMapper,
                                @Qualifier("patientRepo") PatientDao patientDao,
                                @Qualifier("DoctorJpa") DoctorDao doctorDao,
                                @Qualifier("bedRepo") BedDao bedDao,
                                @Qualifier("wardRepo") WardDao wardDao) {
        this.prematureBabyDao = prematureBabyDao;
        this.prematureBabyDtoMapper = prematureBabyDtoMapper;
        this.patientDao = patientDao;
        this.doctorDao = doctorDao;
        this.bedDao = bedDao;
        this.wardDao = wardDao;
    }

    public void createPrematureBaby(PrematureBabyCreateRequest request) {
        Patient patient = patientDao.getPatientById(request.patientId()).orElseThrow(() -> new RuntimeException("Patient not found"));
        Ward ward = wardDao.findWardById(request.wardId()).orElseThrow(() -> new RuntimeException("Ward not found"));
        Bed bed = bedDao.getBedById(request.bedId()).orElseThrow(() -> new RuntimeException("Bed not found"));
        Doctor doctor = doctorDao.findDoctorById(request.doctorId()).orElseThrow(() -> new RuntimeException("Doctor not found"));
        PrematureBaby prematureBaby = new PrematureBaby();
        PrematureBabyDate babyDate = new PrematureBabyDate();
        prematureBaby.setPrematureBabyDate(babyDate);
        prematureBaby.getPrematureBabyDate().setBirthDate(request.birthDate());
        prematureBaby.getPrematureBabyDate().setBirthTime(request.birthTime());
        prematureBaby.setName(request.name());
        prematureBaby.setHeadCircumference(request.headCircumference());
        prematureBaby.setWeight(request.weight());
        prematureBaby.setHeight(request.height());
        prematureBaby.setGender(request.gender());
        prematureBaby.setDoctor(doctor);

        prematureBaby.setPatient(patient);
        prematureBaby.setWard(ward);
        bed.setPrematureBaby(prematureBaby);
        prematureBaby.setBed(bed);

        List<PrematureBaby> prematureBabyList = patient.getPrematureBabies();
        if (prematureBabyList == null) {
            prematureBabyList = new ArrayList<>();
        }
        prematureBabyList.add(prematureBaby);
        prematureBabyDao.createPrematureBaby(prematureBaby);
    }

    public void updatePrematureBaby(PrematureBabyUpdateRequest request) {
        PrematureBaby prematureBaby = prematureBabyDao.findPrematureBabyById(request.id()).orElseThrow(() -> new RuntimeException("Premature baby not found"));
        boolean isChanged = false;

        if (request.name() != null) {
            prematureBaby.setName(request.name());
            isChanged = true;
        }
        if (request.headCircumference() != null) {
            prematureBaby.setHeadCircumference(request.headCircumference());
            isChanged = true;
        }
        if (request.weight() != null) {
            prematureBaby.setWeight(request.weight());
            isChanged = true;
        }
        if (request.height() != null) {
            prematureBaby.setHeight(request.height());
            isChanged = true;
        }
        if (request.gender() != null) {
            prematureBaby.setGender(request.gender());
            isChanged = true;
        }
        if (request.birthDate() != null) {
            prematureBaby.getPrematureBabyDate().setBirthDate(request.birthDate());
            isChanged = true;
        }
        if (request.birthTime() != null) {
            prematureBaby.getPrematureBabyDate().setBirthTime(request.birthTime());
            isChanged = true;
        }

        if (request.patientId() != null) {
            Patient patient = patientDao.getPatientById(request.patientId()).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
            prematureBaby.setPatient(patient);
            isChanged = true;
        }
        if (request.wardId() != null) {
            Ward ward = wardDao.findWardById(request.wardId()).orElseThrow(() -> new ResourceNotFoundException("Ward not found"));
            prematureBaby.setWard(ward);
            isChanged = true;
        }
        if (request.bedId() != null) {
            Bed bed = bedDao.getBedById(request.bedId()).orElseThrow(() -> new ResourceNotFoundException("Bed not found"));
            prematureBaby.setBed(bed);
            isChanged = true;
        }
        if (request.doctorId() != null) {
            Doctor doctor = doctorDao.findDoctorById(request.doctorId()).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
            prematureBaby.setDoctor(doctor);
            isChanged = true;
        }
        if (isChanged) {
            prematureBabyDao.updatePrematureBaby(prematureBaby);
        }
    }

    public void deletePrematureBabyById(Long id) {
        prematureBabyDao.deletePrematureBabyById(id);
    }

    public PrematureBabyDto findPrematureBabyById(Long id) {
        PrematureBaby prematureBaby = prematureBabyDao.findPrematureBabyById(id).orElseThrow(
                () -> new ResourceNotFoundException("Premature baby not found"));
        return prematureBabyDtoMapper.apply(prematureBaby);
    }

    public PrematureBabyDto findPrematureBabyByBedId(Long bedId) {
        PrematureBaby prematureBaby = prematureBabyDao.findPrematureBabyByBedId(bedId).orElseThrow(
                () -> new ResourceNotFoundException("Premature baby not found"));
        return prematureBabyDtoMapper.apply(prematureBaby);
    }

    public List<PrematureBabyDto> findPrematureBabyByPatientId(Long patientId) {
        return prematureBabyDao.findPrematureBabyByPatientId(patientId).stream().map(prematureBabyDtoMapper).toList();
    }

    public Page<PrematureBabyDto> findAllPrematureBaby(Pageable pageable) {
        return prematureBabyDao.findAllPrematureBaby(pageable).map(prematureBabyDtoMapper);
    }

    public Page<PrematureBabyDto> findPrematureBabyByWardId(Long wardId, Pageable pageable) {
        return prematureBabyDao.findPrematureBabyByWardId(wardId, pageable).map(prematureBabyDtoMapper);
    }


    public void dischargePrematureBaby(Long id) {
        PrematureBaby prematureBaby = prematureBabyDao.findPrematureBabyById(id).orElseThrow(
                () -> new ResourceNotFoundException("Premature baby not found"));
        prematureBaby.setIsDischarged(true);
        Long bedId = prematureBaby.getBed().getId();
        Bed bed = bedDao.getBedById(bedId).orElseThrow(
                () -> new ResourceNotFoundException("Bed not found"));
        bed.setPrematureBaby(null);
        bedDao.updateBed(bed);
        prematureBabyDao.updatePrematureBaby(prematureBaby);
    }
}
