package com.azu.hospital.all_user_sevices.employee.doctors.services;


import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.CustomDoctorSpecialDto;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.CustomDoctorSpecialDtoMapper;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDto;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDtoMapper;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service("DoctorService")
public class DoctorService {

    private final DoctorDao doctorDao;
    private final DoctorDtoMapper doctorDtoMapper;

    private final CustomDoctorSpecialDtoMapper mapper;


    @Autowired
    public DoctorService(
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            DoctorDtoMapper doctorDtoMapper, CustomDoctorSpecialDtoMapper mapper) {
        this.doctorDao = doctorDao;
        this.doctorDtoMapper = doctorDtoMapper;
        this.mapper = mapper;
    }


    public Page<DoctorDto> getAllDoctors(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String doctorname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String specialist,
            @RequestParam(required = false) String subSpecialist,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String gender
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Doctor> doctorsPage = doctorDao.findAllDoctorsBy(
                doctorname,
                email,
                specialist,
                subSpecialist,
                bloodGroup,
                mobile,
                gender,
                pageable
        );


        return doctorsPage.map(doctorDtoMapper);
    }

    public Page<CustomDoctorSpecialDto> getAllDoctors(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Doctor> doctorsPage = doctorDao.findAll(
                pageable
        );


        return doctorsPage.map(mapper);
    }



    public List<DoctorDto> getListOfDoctorByIds(List<Long> ids){
        List<Doctor> doctors = doctorDao.getDoctorByIdList(ids);

        return doctors
                .stream()
                .map(doctorDtoMapper)
                .toList();
    }



}
