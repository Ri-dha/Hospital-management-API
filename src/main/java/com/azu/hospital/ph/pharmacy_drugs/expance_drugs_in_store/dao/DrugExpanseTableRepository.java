package com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.dao;


import com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.entity.DrugExpanseTable;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

@Transactional
public interface DrugExpanseTableRepository extends JpaRepository<DrugExpanseTable, Long> {


    @Query("SELECT det FROM DrugExpanseTable det " +
            "JOIN det.drugsItem deist " +
            "WHERE (:drugTradeName IS NULL OR LOWER(deist.itemName) = LOWER(:drugTradeName)) " +
            "AND (:drugScientificName IS NULL OR LOWER(deist.drugScientificName) = LOWER(:drugScientificName)) " +
            "AND (:updateAt IS NULL OR det.updateAt = :updateAt) " +
            "AND (:date IS NULL OR FUNCTION('DATE', det.updateAt) = FUNCTION('DATE', :date))")
    Page<DrugExpanseTable> getAllExpanseDrugs(
            @Param("drugTradeName") String drugTradeName,
            @Param("drugScientificName") String drugScientificName,
            @Param("updateAt") Instant updateAt,
            @Param("date") String date,
            Pageable pageable
    );


    @Query("SELECT dest FROM DrugExpanseTable dest " +
            "WHERE dest.drugsItem.id = :drugId")
    Optional<DrugExpanseTable> getExpanseInStoreTableByDrugIdOfMainTable(@Param("drugId") Long drugId);
}
