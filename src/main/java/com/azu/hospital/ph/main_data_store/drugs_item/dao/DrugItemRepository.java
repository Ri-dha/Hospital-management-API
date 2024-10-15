package com.azu.hospital.ph.main_data_store.drugs_item.dao;

import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DrugItemRepository extends JpaRepository<DrugsItem, Long> {


    @Query("SELECT d FROM DrugsItem d WHERE (:drugId IS NULL OR d.id = :drugId) " +
            "AND (:drugTradeName IS NULL OR LOWER(d.itemName) LIKE LOWER(CONCAT('%', :drugTradeName, '%'))) " +
            "AND (:drugScientificName IS NULL OR LOWER(d.drugScientificName) LIKE LOWER(CONCAT('%', :drugScientificName, '%'))) " +
            "AND (:barcode IS NULL OR lower(d.barcode) like (concat('%',:barcode, '%') ) ) " +
            "ORDER BY COALESCE(d.updateAt, d.createdAt) DESC")
    Page<DrugsItem> findAllByDrugAndOrderBy(@Param("drugId") Long drugId,
                                            @Param("drugTradeName") String drugTradeName,
                                            @Param("drugScientificName") String drugScientificName,
                                            @Param("barcode") String barcode,
                                            Pageable pageable);




    @Query("SELECT d FROM DrugsItem d WHERE TO_DATE(d.expDate, 'YYYY-MM-DD') <= CURRENT_DATE")
    Page<DrugsItem> findAllByExpDate(Pageable pageable);


    @Query("SELECT d FROM DrugsItem d WHERE d.expDate = :expDate ORDER BY d.updateAt DESC")
    List<DrugsItem> findExpiredByYearMonthDay(@Param("expDate") String expDate);


//    @Query("SELECT b FROM DrugsItem b WHERE b.barcode = :barcode ORDER BY b.createdAt DESC")
    Optional<DrugsItem> findFirstByBarcodeOrderByCreatedAtDesc(@Param("barcode") String barcode);

    @Query("SELECT i FROM DrugsItem i WHERE i.isBack = :isBack")
    List<DrugsItem> findAllByIsBack(@Param("isBack") boolean isBack);


    @Query("SELECT COALESCE(COUNT(ds), 0) FROM DrugsItem ds WHERE ds.isBack = true")
    Long countAllBackDrugs();

    @Query("SELECT COUNT(si) FROM DrugsItem ds LEFT JOIN ds.soldItems si")
    Long countSoldItemsByDrugId();


    @Query("SELECT COUNT(dh) FROM DrugsItem ds LEFT JOIN ds.drugRequestHandler dh")
    Long countAllDrugRequests();

    @Query("SELECT COALESCE(COUNT(ds), 0) FROM DrugsItem ds WHERE TO_DATE(ds.expDate, 'YYYY-MM-DD') <= CURRENT_DATE")
    Long countAllExpiredDrugs();

    @Query("SELECT COALESCE(COUNT(ds.id), 0) FROM DrugsItem ds")
    Long countAllDrug();

    @Query("SELECT d FROM DrugsItem d WHERE d.drugSellingPrice IS NOT NULL")
    Page<DrugsItem> findAllByDrugSellingPrice(Pageable pageable);
}
