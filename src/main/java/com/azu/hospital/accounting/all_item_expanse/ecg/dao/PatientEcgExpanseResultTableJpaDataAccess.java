package com.azu.hospital.accounting.all_item_expanse.ecg.dao;

import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.ecg.entity.PatientEcgExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("PatientEcgExpanseResultTableJpa")
public class PatientEcgExpanseResultTableJpaDataAccess implements PatientEcgExpanseResultTableDao {

    private final PatientEcgExpanseResultTableRepository repository;

    @Autowired
    public PatientEcgExpanseResultTableJpaDataAccess(PatientEcgExpanseResultTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPatientEcgExpanseResultTable(PatientEcgExpanseResultTable request) {
        repository.save(request);
    }

    @Override
    public void updatePatientEcgExpanseResultTable(PatientEcgExpanseResultTable update) {
       repository.save(update);
    }

    @Override
    public Optional<PatientEcgExpanseResultTable> getPatientEcgExpanseResultTableById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PatientEcgExpanseResultTable> getAllPatientEcgExpanseResultTableByPatientId(Long patientId , StockBillState state) {
        return repository.findAllByPatientId(patientId , state);
    }

    @Override
    public Optional<PatientEcgExpanseResultTable> getPatientEcgExpanseResultTableByEcgId(Long drugId) {
        return repository.findPatientDrugsExpanseResultTableByDrugId(drugId);
    }

    @Override
    public List<PatientEcgExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId) {
        return repository.findAllPatientExpansesExpanseResultTableByPatientId(patientId);
    }

    @Override
    public List<PatientEcgExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId) {
        return repository.findAllByPatientIdAndIsArchivedTrue(patientId);
    }

    @Override
    public List<PatientEcgExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date) {
        return repository.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patientId, date);
    }
}
