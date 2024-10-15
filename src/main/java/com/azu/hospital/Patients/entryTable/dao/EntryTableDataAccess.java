package com.azu.hospital.Patients.entryTable.dao;


import com.azu.hospital.Patients.entryTable.entity.EntryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("EntryTableJpa")
public class EntryTableDataAccess implements EntryTableDao {
    private final EntryTableRepository entryTableRepository;

    @Autowired
    public EntryTableDataAccess(@Qualifier("entryTableRepository") EntryTableRepository entryTableRepository) {
        this.entryTableRepository = entryTableRepository;
    }


    @Override
    public void createEntryTable(EntryTable entryTable) {
        entryTableRepository.save(entryTable);
    }

    @Override
    public void updateEntryTable(EntryTable entryTable) {
        entryTableRepository.save(entryTable);
    }

    @Override
    public Optional<EntryTable> getEntryTableByPatientID(Long patientId) {
        return entryTableRepository.getEntryTableByPatientIda(patientId);
    }

    @Override
    public Optional<EntryTable> getEntryTableByPatientIdAndEntryDateIsNull(Long patientId) {
        return entryTableRepository.getEntryTableByPatientIdAndEntryDateIsNull(patientId);
    }

    @Override
    public List<EntryTable> getAllEntryTableByPatientIdAndEntryDateIsNotNull(Long patientId) {
        return entryTableRepository.getEntryTableByPatientIdAndEntryDateIsNotNull(patientId) ;
    }

    @Override
    public Optional<EntryTable> getEntryTableByPatientIdAAndEntryDateIsNotNull(Long patientId) {
        return entryTableRepository.getEntryTableByPatientIdAAndEntryDateIsNotNull(patientId);
    }

    @Override
    public Optional<EntryTable> getEntryTableById(Long id) {
        return entryTableRepository.getEntryTableById(id);
    }
}
