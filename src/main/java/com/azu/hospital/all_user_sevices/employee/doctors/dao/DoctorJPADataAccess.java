package com.azu.hospital.all_user_sevices.employee.doctors.dao;


import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("DoctorJpa")
public class DoctorJPADataAccess implements DoctorDao {

    private final DoctorRepository doctorRepository;


    @Autowired
    public DoctorJPADataAccess(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor createNewDoctor(Doctor request) {
        return doctorRepository.save(request);
    }

    @Override
    public Page<Doctor> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }


    @Override
    public void updateCurrentDoctor(Doctor update) {
        doctorRepository.save(update);
    }


    @Override
    public void deleteExistDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public List<Doctor> getDoctorByIdList(List<Long> doctorIds) {
        return doctorRepository.getDoctorByIdList(doctorIds);
    }

    @Override
    public Optional<Doctor> findDoctorById(Long id) {
        return  doctorRepository.findDoctorById(id);
    }

    @Override
    public Optional<Doctor> findByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }

    @Override
    public Optional<Doctor> findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }


    @Override
    public Boolean existsByUsername(String username) {
        return doctorRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return doctorRepository.existsByEmail(email);
    }

    @Override
    public List<Doctor> findByUsernameContainingOrEmailContaining(String username, String email) {
        return doctorRepository.findByUsernameContainingOrEmailContaining(username, email);
    }


    @Override
    public Boolean existsByMobile(String mobile) {
        return doctorRepository.existsByMobile(mobile);
    }

    @Override
    public Page<Doctor> findAllDoctorsBy(
            String username, String email,
            String specialist, String subSpecialist,
            String bloodGroup, String mobile, String gender, Pageable pageable
    ) {
        return doctorRepository.findDoctorsBy(username, email, bloodGroup, subSpecialist,bloodGroup, mobile, gender,
                pageable);
    }

    @Override
    public Page<Doctor> findAll(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public Optional<Doctor> findDoctorsByDoctorIdAndRoles(Long id, EnumRole roles) {
        return doctorRepository.findDoctorsByDoctorIdAndRoles(id , roles);
    }

    @Override
    public Optional<Doctor> getDoctorByToken(String token) {
        return Optional.empty();
                //doctorRepository.findDoctorByTokens(token);
    }


}
