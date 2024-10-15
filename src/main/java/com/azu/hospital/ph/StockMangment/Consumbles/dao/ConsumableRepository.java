package com.azu.hospital.ph.StockMangment.Consumbles.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumables, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Consumables c WHERE c.barcode = :barcode")
    boolean consumableExistByBarcode(@Param("barcode")String barcode);

    @Query("SELECT c FROM Consumables c WHERE " +
            "(:consumableName IS NULL OR LOWER(c.consumableName) LIKE %:consumableName%) " +
            "AND (:barcode IS NULL OR LOWER(c.barcode) LIKE %:barcode%)")
    Page<Consumables> findByConsumableSortedBOrderBy(
            @Param("consumableName") String consumableName,
            @Param("barcode") String barcode,
            Pageable pageable);

    @Query("SELECT c FROM Consumables c WHERE c.consumableId = :consumableId")
    List<Consumables> getConsumablesByBillId(@Param("consumableId") Long consumableId);

    @Query("SELECT c FROM Consumables c WHERE TRIM(LOWER(c.barcode)) = TRIM(LOWER(:barcode))")
    Optional<Consumables> findConsumablesByBarcode(String barcode);


    @Query("SELECT c FROM Consumables c WHERE lower(c.consumableName) LIKE lower(concat('%', :consumableName, '%'))")
    Optional<Consumables> findConsumablesByConsumableNameContainingIgnoreCase(@Param("consumableName") String consumableName);

    @Query("SELECT c FROM Consumables c WHERE " +
            "(:consumableName IS NULL OR LOWER(c.consumableName) LIKE LOWER(CONCAT('%', :consumableName, '%'))) " +
            "AND (:barcode IS NULL OR c.barcode LIKE CONCAT('%', :barcode, '%'))" + "ORDER BY c.updateAt DESC")
    Page<Consumables> selectAllConsumables(
            @Param("consumableName") String consumableName,
            @Param("barcode") String barcode,
            Pageable pageable);

}
