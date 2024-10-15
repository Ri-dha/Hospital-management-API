package com.azu.hospital.ph.main_data_store.drugs_item.dao;

import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DrugItemDao {

    Page<DrugsItem> findAllByDrugAndOrderBy(
            Long drugId, String drugTradeName, String drugScientificName, String barcode, Pageable pageable
    );

    void insertDrug(DrugsItem request);

    void updateDrug(DrugsItem update);

    Page<DrugsItem> getAllExpireDate(Pageable pageable);

    Optional<DrugsItem> findDrugByBarcode(String barcode);

    List<DrugsItem> findAllRefundDrugs(boolean isBack);

    List<DrugsItem> findExpiredByYearMonthDay(String expDate);

    Optional<DrugsItem> selectDrudById(Long drugId);

    List<DrugsItem> selectAllDrugsForSetQuantity();

    Long countAllDrugBack();

    Long countAllDrugExpire();

    Long countAllDrugRequest();

    Long countAllDrugSold();

    Long countAllDrug();

    Page<DrugsItem> getAllDrugWithPrice(Pageable pageable);

}
