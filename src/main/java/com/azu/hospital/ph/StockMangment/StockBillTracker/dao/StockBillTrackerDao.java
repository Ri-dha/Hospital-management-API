package com.azu.hospital.ph.StockMangment.StockBillTracker.dao;

import com.azu.hospital.ph.StockMangment.StockBillTracker.entity.StockBillTracker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StockBillTrackerDao {

    void createStockBillTracker(StockBillTracker tracker);

    void updateStockBillTracker(StockBillTracker tracker);

    void deleteStockBillTracker(Long id);

    List<StockBillTracker> getAllStockBillTrackers();

    List<StockBillTracker> getStockBillTrackerByBillId(Long billId);




}
