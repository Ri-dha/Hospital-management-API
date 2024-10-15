package com.azu.hospital.ph.StockMangment.StockBillTracker.dao;

import com.azu.hospital.ph.StockMangment.StockBillTracker.entity.StockBillTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StockBillTrackerRepository extends JpaRepository<StockBillTracker, Long> {

    @Query("SELECT b FROM StockBillTracker b WHERE b.billId = :billId")
    List<StockBillTracker> getStockBillTrackerByBillId(Long billId);


}
