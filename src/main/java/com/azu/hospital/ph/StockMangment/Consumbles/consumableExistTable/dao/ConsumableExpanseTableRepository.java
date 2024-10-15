package com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity.ConsumableExpanseTable;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ConsumableExpanseTableRepository extends JpaRepository<ConsumableExpanseTable, Long> {

    @Query("SELECT c FROM ConsumableExpanseTable c WHERE c.mainsConsumables.consumableName = :name AND c.mainsConsumables.barcode = :barcode ORDER BY c.updateAt DESC")
    List<ConsumableExpanseTable> findAllConsumablesAndSort(@Param("name") String name, @Param("barcode") String barcode, Pageable pageable);


    @Query("SELECT c from ConsumableExpanseTable c WHERE c.mainsConsumables.barcode = :barcode")
    Optional<ConsumableExpanseTable> findByBarcode(@Param("barcode")String barcode);

    @Query("SELECT c from ConsumableExpanseTable c WHERE c.mainsConsumables.consumableId = :id")
    List<ConsumableExpanseTable> findByMainsConsumables(Long id);
}

