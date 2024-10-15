package com.azu.hospital.accounting.all_item_expanse.drugs.dao;

import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("PatientDrugsExpanseResultTableJpa")
public class PatientDrugsExpanseResultTableJpaDataAccess implements PatientDrugsExpanseResultTableDao{

    private final PatientDrugsExpanseResultTableRepository repository;

    @Autowired
    public PatientDrugsExpanseResultTableJpaDataAccess(PatientDrugsExpanseResultTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPatientDrugsExpanseResultTable(PatientDrugsExpanseResultTable request) {
        repository.save(request);
    }

    @Override
    public void updatePatientDrugsExpanseResultTable(PatientDrugsExpanseResultTable update) {
       repository.save(update);
    }



    @Override
    public List<PatientDrugsExpanseResultTable> getAllPatientDrugsExpanseResultTableByPatientId(Long patientId , StockBillState state) {
        return repository.findAllByPatientId(patientId , state);
    }




    @Override
    public List<PatientDrugsExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId) {
        return repository.findAllPatientExpansesExpanseResultTableByPatientId(patientId);
    }



    @Override
    public Optional<PatientDrugsExpanseResultTable> getPatientDrugsExpanseResultTableByDrugIdAndPriceAndPatientId(Long drugId, BigDecimal price, Long patientId) {
        return repository.findPatientDrugsExpanseResultTableByPatientId(patientId);
    }

    @Override
    public List<PatientDrugsExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId) {
        return repository.findAllByPatientIdAndIsArchivedTrue(patientId);
    }

    @Override
    public List<PatientDrugsExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId) {
        return repository.findAllByPatientIdAndIsArchivedIsTrueAnd(patientId);
    }


}
