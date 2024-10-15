package com.azu.hospital.lab_collection.internal.int_tests_request.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.internal.dao.InternalLabDao;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dao.IntTestRequestDao;
import com.azu.hospital.lab_collection.internal.int_tests_request.dto.IntTestRequestDto;
import com.azu.hospital.lab_collection.internal.int_tests_request.dto.IntTestRequestDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntTestRequestService {

    private final IntTestRequestDao intTestRequestDao;


    private final InternalLabDao internalLabDao;

    private final IntTestRequestDtoMapper mapper;



    @Autowired
    public IntTestRequestService(
            @Qualifier("internalTestRequestRepo") IntTestRequestDao intTestRequestDao,
            @Qualifier("internalLabRepo") InternalLabDao internalLabDao,
            @Qualifier("intTestRequestDtoMapper") IntTestRequestDtoMapper mapper
    ) {
        this.intTestRequestDao = intTestRequestDao;
        this.internalLabDao = internalLabDao;
        this.mapper = mapper;
    }

    public List<IntTestRequestDto> getTestsByIntTestId(Long internalTestId) {
        InternalLabTest internalLabTest = internalLabDao.findById(internalTestId).orElseThrow(
                () -> new ResourceNotFoundException("Test Doesn't Exists")
        );

        return internalLabTest.getTestRequests().
                stream().
                map(mapper::toDto).
                collect(Collectors.toList());
    }

    public List<IntTestRequestDto> getIntTestRequestsByInternalLabTestId(Long labTestId) {
        return intTestRequestDao.getAllByInternalLabTestId(labTestId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
