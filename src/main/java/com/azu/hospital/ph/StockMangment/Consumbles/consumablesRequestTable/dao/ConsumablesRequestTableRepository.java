package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface ConsumablesRequestTableRepository extends JpaRepository<ConsumablesRequestTable, Long> {

    @Query("SELECT c FROM ConsumablesRequestTable c WHERE c.requestStatus = :status")
    Page<ConsumablesRequestTable> findAll(String status, Pageable pageable);
}
