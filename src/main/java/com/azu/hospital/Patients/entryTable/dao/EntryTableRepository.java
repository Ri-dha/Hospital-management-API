package com.azu.hospital.Patients.entryTable.dao;


import com.azu.hospital.Patients.entryTable.entity.EntryTable;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface EntryTableRepository extends JpaRepository<EntryTable, Long>{


    @Query("SELECT e FROM EntryTable e WHERE e.patient.id = :patientId")
    Optional<EntryTable> getEntryTableByPatientIda(@Param("patientId") Long patientId);

    @Query("SELECT e FROM EntryTable e WHERE e.id = :id")
    Optional<EntryTable> getEntryTableById(Long id);

    @Query("SELECT e FROM EntryTable e WHERE e.patient.id = :patientId AND e.entryDate IS NULL")
    Optional<EntryTable> getEntryTableByPatientIdAndEntryDateIsNull(@Param("patientId") Long patientId);

    @Query("SELECT e FROM EntryTable e WHERE e.patient.id = :patientId AND e.entryDate IS NOT NULL")
    Optional<EntryTable> getEntryTableByPatientIdAAndEntryDateIsNotNull(Long patientId);
    @Query("SELECT e FROM EntryTable e WHERE e.patient.id = :patientId AND e.entryDate IS NOT NULL")
    List<EntryTable> getEntryTableByPatientIdAndEntryDateIsNotNull(Long patientId);
}
