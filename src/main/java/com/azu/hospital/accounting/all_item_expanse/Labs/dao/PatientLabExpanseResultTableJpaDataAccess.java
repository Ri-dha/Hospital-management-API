package com.azu.hospital.accounting.all_item_expanse.Labs.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.Labs.entity.PatientLabExpanseResultTable;

import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("PatientLabExpanseResultTableJpa")
public class PatientLabExpanseResultTableJpaDataAccess implements PatientLabExpanseResultTableDao {

    private final PatientLabExpanseResultTableRepository repository;

    @Autowired
    public PatientLabExpanseResultTableJpaDataAccess(PatientLabExpanseResultTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPatientLabExpanseResultTable(PatientLabExpanseResultTable request) {
        repository.save(request);
    }

    @Override
    public void updatePatientLabExpanseResultTable(PatientLabExpanseResultTable update) {
       repository.save(update);
    }

    @Override
    public Optional<PatientLabExpanseResultTable> getPatientLabExpanseResultTableById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PatientLabExpanseResultTable> getAllPatientLabExpanseResultTableByPatientId(Long patientId , StockBillState state) {
        return repository.findAllByPatientId(patientId , state);
    }

    @Override
    public Optional<PatientLabExpanseResultTable> getPatientLabExpanseResultTableByLabId(Long patientId) {
        return repository.findPatientLabsExpanseResultTableByPatientId(patientId);
    }

    @Override
    public List<PatientLabExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId) {
        return repository.findAllPatientExpansesExpanseResultTableByPatientId(patientId);
    }

    @Override
    public Optional<PatientLabExpanseResultTable> getPatientLabExpanseResultTableByLabIdAndPatientId(Long labId, Long patientId) {
        return repository.findPatientLabExpanseResultTableByLabIdAndPatientId(labId, patientId);
    }

    @Override
    public List<PatientLabExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId) {
        return repository.findAllByPatientIdAndIsArchivedTrue(patientId);
    }

    @Override
    public List<PatientLabExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date) {
        return repository.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patientId, date);
    }


}
