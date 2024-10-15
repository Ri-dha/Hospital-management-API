package com.azu.hospital.Patients.charts.operativeNote.dao;

import com.azu.hospital.Patients.charts.operativeNote.entity.OperativeNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperativeNoteRepository extends JpaRepository<OperativeNote, Long> {


    List<OperativeNote> findAllByPatientId(@Param("patientId") Long patientId);
}
