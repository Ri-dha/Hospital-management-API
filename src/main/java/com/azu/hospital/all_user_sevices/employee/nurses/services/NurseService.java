package com.azu.hospital.all_user_sevices.employee.nurses.services;


import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.all_user_sevices.employee.nurses.dto.NurseDto;
import com.azu.hospital.all_user_sevices.employee.nurses.dto.NurseDtoMapper;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service("NurseService")
public class NurseService {

    private final NurseDao nurseDao;
    private final NurseDtoMapper nurseDtoMapper;

    public NurseService(
            @Qualifier("NurseJpa") NurseDao nurseDao,
            NurseDtoMapper nurseDtoMapper) {
        this.nurseDao = nurseDao;
        this.nurseDtoMapper = nurseDtoMapper;
    }



    public Page<NurseDto> getAllNurses(
            Pageable pageable,
            String nursename,
            String email,
            String specialist,
            String bloodGroup,
            String mobile,
            String gender
    ) {


        Page<Nurse> nursesPage = nurseDao.findAllNursesBy(
                nursename,
                email,
                specialist,
                bloodGroup,
                mobile,
                gender,
                pageable
        );

        List<NurseDto> dtoList = nursesPage
                .stream()
                .map(nurseDtoMapper)
                .collect(Collectors.toList());

        Long count = nurseDao.countAllItems();
        return new PageImpl<>(dtoList, pageable, count);
    }










}
