package com.azu.hospital.Patients.entryTable.dao;


import com.azu.hospital.Patients.entryTable.entity.EntryTable;

import java.util.List;
import java.util.Optional;

public interface EntryTableDao {
    void createEntryTable(EntryTable entryTable);

    void updateEntryTable(EntryTable entryTable);
    Optional<EntryTable> getEntryTableByPatientID(Long patientId);

    Optional<EntryTable> getEntryTableByPatientIdAndEntryDateIsNull(Long patientId);

    List<EntryTable> getAllEntryTableByPatientIdAndEntryDateIsNotNull(Long patientId);
    Optional<EntryTable> getEntryTableByPatientIdAAndEntryDateIsNotNull(Long patientId);
    Optional<EntryTable> getEntryTableById(Long id);
}
