package com.azu.hospital.accounting.all_item_expanse.other_consumbles.dao;

import com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity.PatientOtherConsumablesResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("PatientOtherConsumablesResultTableJpa")
public class PatientOtherConsumablesResultTableRepositoryJpaDataAccess implements PatientOtherConsumablesResultTableDao {

    private final PatientOtherConsumablesTableRepository repository;

    public PatientOtherConsumablesResultTableRepositoryJpaDataAccess(PatientOtherConsumablesTableRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createPatientOtherConsumablesResultTable(PatientOtherConsumablesResultTable request) {
        repository.save(request);
    }

    @Override
    public void updatePatientOtherConsumablesResultTable(PatientOtherConsumablesResultTable update) {
       repository.save(update);
    }

    @Override
    public void updatePatientOtherConsumablesResult(List<PatientOtherConsumablesResultTable> update) {
        repository.saveAll(update);
    }

    @Override
    public Optional<PatientOtherConsumablesResultTable> getPatientOtherConsumablesResultTableById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PatientOtherConsumablesResultTable> getAllPatientOtherConsumablesResultTableByPatientId(Long patientId , StockBillState state) {
        return repository.findAllByPatientId(patientId , state);
    }

    @Override
    public Optional<PatientOtherConsumablesResultTable> getPatientOtherConsumablesResultTableByDrugId(Long drugId) {
        return repository.findPatientExpansesExpanseResultTableByExpansesId(drugId);
    }

    @Override
    public List<PatientOtherConsumablesResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId) {
        return repository.findAllPatientExpansesExpanseResultTableByPatientId(patientId);
    }

    @Override
    public Optional<PatientOtherConsumablesResultTable> getPatientOtherConsumablesResultTableByDrugIdAndPatientId(Long itemId, Long patientId) {
        return repository.findPatientExpansesExpanseResultTableByExpansesIdAndPatientId(itemId , patientId);
    }

    @Override
    public Optional<PatientOtherConsumablesResultTable> findByNameAndPatientId(Long itemId, Long patientId) {
        return repository.findByNameAndPatientId(itemId , patientId);
    }

    @Override
    public List<PatientOtherConsumablesResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId) {
        return repository.findAllByPatientIdAndIsArchivedTrue(patientId);
    }

    @Override
    public List<PatientOtherConsumablesResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date) {
        return repository.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patientId, date);
    }


}
