package com.azu.hospital.ph.main_data_store.drugs_item.dao;

import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("DrugItemJpa")
public class DrugItemJpaDataAccess implements DrugItemDao{

    private final DrugItemRepository repository;

    @Autowired
    public DrugItemJpaDataAccess(DrugItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<DrugsItem> findAllByDrugAndOrderBy(
            Long drugId, String drugTradeName, String drugScientificName, String barcode, Pageable pageable
    ) {
        return repository.findAllByDrugAndOrderBy(drugId, drugTradeName, drugScientificName, barcode,pageable);
    }

    @Override
    public void insertDrug(DrugsItem request) {
       repository.save(request);
    }

    @Override
    public void updateDrug(DrugsItem update) {
        repository.save(update);
    }

    @Override
    public Page<DrugsItem> getAllExpireDate(Pageable pageable) {
        return repository.findAllByExpDate(pageable);
    }

    @Override
    public Optional<DrugsItem> findDrugByBarcode(String barcode) {
        return repository.findFirstByBarcodeOrderByCreatedAtDesc(barcode);
    }

    @Override
    public List<DrugsItem> findAllRefundDrugs(boolean isBack) {
        return repository.findAllByIsBack(isBack);
    }

    @Override
    public List<DrugsItem> findExpiredByYearMonthDay(String expDate) {
        return repository.findExpiredByYearMonthDay(expDate);
    }

    @Override
    public Optional<DrugsItem> selectDrudById(Long drugId) {
        return repository.findById(drugId);
    }

    @Override
    public List<DrugsItem> selectAllDrugsForSetQuantity() {
        return repository.findAll();
    }

    @Override
    public Long countAllDrugBack() {
        return repository.countAllBackDrugs();
    }

    @Override
    public Long countAllDrugExpire() {
        return repository.countAllExpiredDrugs();
    }

    @Override
    public Long countAllDrugRequest() {
        return repository.countAllDrugRequests();
    }

    @Override
    public Long countAllDrugSold() {
        return repository.countSoldItemsByDrugId();
    }

    @Override
    public Long countAllDrug() {
        return repository.countAllDrug();
    }

    @Override
    public Page<DrugsItem> getAllDrugWithPrice(Pageable pageable) {
        return repository.findAllByDrugSellingPrice(pageable);
    }

}
