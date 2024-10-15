package com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.dao;

import com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.entity.DrugExpanseTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

public interface DrugExpanseInStoreDao {


    Optional<DrugExpanseTable> getExpanseInStoreTableById(Long expanseId);

    void addDrugsToExpanseInStore(DrugExpanseTable request);

    void updateDrugsInExpanseStore(DrugExpanseTable update);

    Optional<DrugExpanseTable> getExpanseInStoreTableByDrugIdOfMainTable(Long drugId);

    Page<DrugExpanseTable> getAllExpanseDrugs(
            String drugTradeName,
            String drugScientificName,
            Instant updateAt,
            String date,
            Pageable pageable
    );

}
