package com.azu.hospital.all_user_sevices.employee.permanent.services;


import com.azu.hospital.all_user_sevices.employee.permanent.dao.PermanentDao;
import com.azu.hospital.all_user_sevices.employee.permanent.dto.PermanentDto;
import com.azu.hospital.all_user_sevices.employee.permanent.dto.PermanentDtoMapper;
import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service("PermanentService")
public class PermanentService {

    private final PermanentDao permanentDao;
    private final PermanentDtoMapper permanentDtoMapper;
    public PermanentService(
            @Qualifier("PermanentJpa") PermanentDao permanentDao,
            PermanentDtoMapper permanentDtoMapper) {
        this.permanentDao = permanentDao;
        this.permanentDtoMapper = permanentDtoMapper;
    }


    public Page<PermanentDto> getAllPermanents(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String permanentname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String specialist,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String gender
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Permanent> permanentsPage = permanentDao.findAllPermanentsBy(
                permanentname,
                email,
                specialist,
                bloodGroup,
                mobile,
                gender,
                pageable
        );

        List<PermanentDto> dtoList = permanentsPage
                .stream()
                .map(permanentDtoMapper)
                .collect(Collectors.toList());

        Long count = permanentDao.countAllItems();
        return new PageImpl<>(dtoList, pageable, count);
    }





}
