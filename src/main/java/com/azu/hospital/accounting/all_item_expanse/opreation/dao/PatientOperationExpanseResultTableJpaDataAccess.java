package com.azu.hospital.accounting.all_item_expanse.opreation.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.opreation.entity.PatientOperationExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("PatientOperationExpanseResultTableJpa")
public class PatientOperationExpanseResultTableJpaDataAccess implements PatientOperationExpanseResultTableDao {

    private final PatientOperationExpanseResultTableRepository repository;

    @Autowired
    public PatientOperationExpanseResultTableJpaDataAccess(PatientOperationExpanseResultTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPatientOperationExpanseResultTable(PatientOperationExpanseResultTable request) {
        repository.save(request);
    }

    @Override
    public void updatePatientOperationExpanseResultTable(PatientOperationExpanseResultTable update) {
       repository.save(update);
    }

    @Override
    public void updatePatientOperationExpanseResult(List<PatientOperationExpanseResultTable> update) {
        repository.saveAll(update);
    }

    @Override
    public Optional<PatientOperationExpanseResultTable> getPatientOperationExpanseResultTableById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PatientOperationExpanseResultTable> getAllPatientOperationExpanseResultTableByPatientId(Long patientId  , StockBillState state) {
        return repository.findAllByPatientId(patientId , state);
    }

    @Override
    public Optional<PatientOperationExpanseResultTable> getPatientOperationExpanseResultTableByPatientId(Long patientId) {
        return repository.findPatientOperationsExpanseResultTableByOperationId(patientId);
    }

    @Override
    public List<PatientOperationExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId) {
        return repository.findAllPatientExpansesExpanseResultTableByPatientId(patientId);
    }

    @Override
    public Optional<PatientOperationExpanseResultTable> getPatientOperationExpanseResultTableByOperationIdAndPatientId(Long operationId, Long patientId) {
        return repository.findPatientOperationExpanseResultTableByOperationIdAndPatientId(operationId, patientId);
    }

    @Override
    public List<PatientOperationExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId) {
        return repository.findAllByPatientIdAndIsArchivedTrue(patientId);
    }

    @Override
    public List<PatientOperationExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date) {
        return repository.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patientId, date);
    }


}
