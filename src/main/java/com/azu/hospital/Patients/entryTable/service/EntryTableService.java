package com.azu.hospital.Patients.entryTable.service;


import com.azu.hospital.Patients.entryTable.dao.EntryTableDao;
import com.azu.hospital.Patients.entryTable.dto.EntryTableDateDto;
import com.azu.hospital.Patients.entryTable.dto.EntryTableDateDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryTableService {

    private final EntryTableDao entryTableDao;

    private final EntryTableDateDtoMapper entryTableDateDtoMapper;
    @Autowired
    public EntryTableService(@Qualifier("EntryTableJpa") EntryTableDao entryTableDao, EntryTableDateDtoMapper entryTableDateDtoMapper) {
        this.entryTableDao = entryTableDao;
        this.entryTableDateDtoMapper = entryTableDateDtoMapper;
    }


    public List<EntryTableDateDto> getALlEntryTableByPatientId(Long patientId) {
        return entryTableDao.getAllEntryTableByPatientIdAndEntryDateIsNotNull(patientId)
                .stream()
                .map(entryTableDateDtoMapper)
                .toList();
    }





}
